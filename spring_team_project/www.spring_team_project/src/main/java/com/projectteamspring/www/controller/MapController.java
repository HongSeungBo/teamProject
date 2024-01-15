package com.projectteamspring.www.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.MapDTO;
import com.projectteamspring.www.domain.MapFileVO;
import com.projectteamspring.www.domain.MapVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;
import com.projectteamspring.www.handler.MapFileHandler;
import com.projectteamspring.www.handler.TmtSubFileHandler;
import com.projectteamspring.www.service.MapService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/kakaomap/*")
@Slf4j
public class MapController {
	
	@Inject
	private MapService msv;
	
	@Inject
	private TmtSubFileHandler tsh;
	
	@Inject
	private MapFileHandler mfh;
	
	@GetMapping("/map")
	public void goMap() {
		log.info("카카카카캌오");
	}
	
	@GetMapping("/register")
	public String goMarkerRegister() {
		log.info("maam");
		return "/kakaomap/markerRegister";
	}
	
	@PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertMarker(@RequestBody MapVO mvo){
		log.info("start >>> insert : "+mvo);
		int isOK = msv.insertMarker(mvo);
		return isOK>0 ?
				new ResponseEntity<String>("1",HttpStatus.OK):
				new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/markerModify")
	public String goMapmarkerModify(long mno,Model model) {
		MapDTO mdto = msv.getClickMarker(mno);
		model.addAttribute("mdto", mdto);
		return "/kakaomap/markerModify";
	}
	
	@PostMapping("/markerModify")
	public void markerModify(MapVO mvo,MapFileVO mfvo,@RequestParam(name="files",required = false) MultipartFile[] files) {
		log.info("markerModify >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+mvo.toString());
		String[] uuids = mfvo.getUuid().split(",");
		int isOK = msv.markerModifyMno(mvo);
		List<MapFileVO> flist = new ArrayList<>();
		if(files == null) {
			return;
		}
		if(files[0].getSize()>0) {
			flist = mfh.MapFiles(files,mvo.getMno());
			if(uuids.length>0) {
				for(String uuid : uuids) {
					log.info(uuid);
					flist.add(msv.getFile(uuid));
				}
			}
			isOK *= msv.markerFileDeleteAll(mfvo.getMno());
			isOK *= msv.insertFiles(flist);
		}
		
//		return "/kakaomap/map";
	}

	
	
	@PostMapping(value = "/insertMarkerFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertMarkerFiles(@RequestParam(name="files",required = false) MultipartFile[] files){
		log.info("start >>> insertMarkerFile :"+files);
		int isOK = 0;
		long smno = msv.setDescMarker();
		log.info("mno"+smno);
		
		List<MapFileVO> flist = new ArrayList<MapFileVO>();
		if(files[0].getSize()>0) {
			flist = mfh.MapFiles(files,smno);
			isOK = msv.insertFiles(flist);
		}
		
		return isOK>0 ?
				new ResponseEntity<String>("1",HttpStatus.OK):
				new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MapVO>> getSearchMarkerList(@RequestBody MapVO mvo){
		log.info("start >>> Search"+mvo);
		List<MapVO> markerList = new ArrayList<MapVO>();
		List<MapVO> list = msv.getSearchMarkerList(mvo);
		list = msv.getSearchMarkerList(mvo);
		if(mvo.getAnimalType().equals("")) {
			markerList = list;
		}else {
			Set<MapVO> set = new HashSet<MapVO>();
			String[] searchList = mvo.getAnimalType().split(" ");
			for(String searType : searchList) {
				log.info("검색한 종 : "+searType);
				for(MapVO mvo1 : list) {
					if (mvo1.getAnimalType() !=null) {
						log.info(mvo1.toString());
						String[] aList = mvo1.getAnimalType().split(",");
						for(String mvoType : aList) {
							log.info("검색중 종 : "+mvoType);
							if(searType.equals(mvoType)) {
								set.add(mvo1);
								log.info("들어감"+mvo1);
								break;
							}
						}
					}
				}
			}
			for(MapVO mvo2 : set) {
				markerList.add(mvo2);
			}
		}
		log.info("마커 : "+markerList);
		return new ResponseEntity<List<MapVO>>(markerList,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getmarker/{mno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MapDTO> getClickMarker(@PathVariable("mno") long mno){
		log.info("start >>> getClickMarker "+mno);
		
		MapDTO mdto = msv.getClickMarker(mno);
		
		return new ResponseEntity<MapDTO>(mdto,HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MapVO>> getMarkerList(){
		log.info("start >>> getMarkerList");
		List<MapVO> list = msv.getMarkerList();
		return  new ResponseEntity<List<MapVO>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value = "/markerFileUpload",produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<TmtFileSaveVO>> markerFileUpload(@RequestParam(name="files",required = false) MultipartFile[] files) {
		log.info("start >>> markerFileUpload : "+files.length);
		
		List<TmtFileSaveVO> flist = new ArrayList<>();
		flist = tsh.markerFiles(files);
		msv.tmtMarkerPorary(flist);
		
		return new ResponseEntity<List<TmtFileSaveVO>>(flist,HttpStatus.OK);
	}
	
	@GetMapping("/markerDelete")
	public String markerDelete(long mno) {
		log.info("start >>> markerDelete : "+mno);
		int isOK = msv.markerDelete(mno);
		isOK *= msv.markerFileDeleteAll(mno);
		return "redirect:/kakaomap/map";
	}
	
	@GetMapping("/DeleteM")
	public String markerDeleteM(long mno) {
		log.info("start >>> markerDelete : "+mno);
		int isOK = msv.markerDelete(mno);
		isOK *= msv.markerFileDeleteAll(mno);
		return "/kakaomap/markerRegister";
	}
	
	@GetMapping(value = "/indexMarker/{cnt}",produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<MapVO>> indexMarker(@PathVariable("cnt") int cnt) {
		log.info("start >>> indexMarker");
		
		List<MapVO> list = msv.indexMarker(cnt);
		
		return new ResponseEntity<List<MapVO>>(list,HttpStatus.OK);
	}
	
}
