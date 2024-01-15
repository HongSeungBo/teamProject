package com.projectteamspring.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.ProductCommentFileVO;
import com.projectteamspring.www.domain.ProductCommentPagingVO;
import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ProductFileVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ProductCommentRecommendVO;
import com.projectteamspring.www.handler.ProductCommentFileHandler;
import com.projectteamspring.www.handler.ProductCommentPagingHandler;
import com.projectteamspring.www.handler.ProductPagingHandler;
import com.projectteamspring.www.service.ProductCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product/*")
@RestController
public class ProductComment {

	@Inject
	private ProductCommentService pcsv;
	
	@Inject
	private ProductCommentFileHandler pcfh;
	
	@PostMapping(value="/registerText", consumes ="application/json",  produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> commentEditText(@RequestBody ProductCommentVO pcvo){
		int isOk = pcsv.registerText(pcvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/registerFile", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> commentEditFile(@RequestParam(name="files", required = false)MultipartFile[] files){
		List<ProductCommentFileVO> flist = new ArrayList<ProductCommentFileVO>();
		if(files!=null && files[0].getSize()>0) {
			flist = pcfh.ProCmtFiles(files);
		}
		int isOk = pcsv.registerFile(flist);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/list/{pno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductCommentPagingHandler> getList(@PathVariable("pno")long pno, @PathVariable("page")int page){
		ProductCommentPagingVO pcpvo = new ProductCommentPagingVO(page, 5);
		ProductCommentPagingHandler pph = pcsv.getList(pno, pcpvo);
		return new ResponseEntity<ProductCommentPagingHandler>(pph, HttpStatus.OK);
	}
	
	@GetMapping(value="/delete/{pcno}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteComment(@PathVariable("pcno")long pcno){
		int isOk = pcsv.deleteCmt(pcno);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/commentSearch/{pno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductCommentPagingHandler> commentSearch(@RequestParam("keyword")String keyword, @RequestParam("type")String type, @PathVariable("pno")long pno, @PathVariable("page")int page){
		ProductCommentPagingVO ppvo = new ProductCommentPagingVO(page, 5);
		ppvo.setKeyword(keyword);
		ppvo.setType(type);
		ProductCommentPagingHandler pph = pcsv.getSearchList(pno, ppvo);
		return new ResponseEntity<ProductCommentPagingHandler>(pph, HttpStatus.OK);
	}
	
	@PostMapping(value="/selectRecommend", consumes ="application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> selectRecommend(@RequestBody ProductCommentRecommendVO prvo){
		int isOk = pcsv.selectRecommend(prvo);
		return new ResponseEntity<Integer>(isOk,HttpStatus.OK);
	}
	
	@PostMapping(value="/addRecommend", consumes ="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addRecommend(@RequestBody ProductCommentRecommendVO prvo){
		int isOk = pcsv.addRecommend(prvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/removeRecommend", consumes ="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeRecomend(@RequestBody ProductCommentRecommendVO prvo){
		int isOk = pcsv.removeRecommend(prvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

//	@GetMapping(value="/commentSearch/{pno}/{page}/{keyword}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<ProductPagingHandler> commentSearch1(@PathVariable("pno")long pno, @PathVariable("page")int page, @PathVariable("keyword")String keyword, @PathVariable("type")String type){
//		ProductPagingVO ppvo = new ProductPagingVO(page, 5);
//		ppvo.setKeyword(keyword);
//		ppvo.setType(type);
//		log.info("page >> " + page);
//		log.info("controller ppvo >>>>>>>>>>>> " + ppvo);
//		ProductPagingHandler pph = pcsv.getSearchList(pno, ppvo);
//		return new ResponseEntity<ProductPagingHandler>(pph, HttpStatus.OK);
//	}
}
