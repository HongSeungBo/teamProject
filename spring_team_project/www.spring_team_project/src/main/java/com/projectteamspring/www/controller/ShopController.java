package com.projectteamspring.www.controller;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.MyCartVO;
import com.projectteamspring.www.domain.MyWishVO;
import com.projectteamspring.www.domain.PayMentVO;
import com.projectteamspring.www.domain.ProductFileVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ShopDTO;
import com.projectteamspring.www.domain.ShopPagingVO;
import com.projectteamspring.www.domain.ShopVO;
import com.projectteamspring.www.domain.StorageCartVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;
import com.projectteamspring.www.handler.ProductFileHandler;

import com.projectteamspring.www.handler.ProductPagingHandler;
import com.projectteamspring.www.handler.ShopPagingHandler;

import com.projectteamspring.www.handler.TmtFileHandler;
import com.projectteamspring.www.handler.TmtSubFileHandler;
import com.projectteamspring.www.service.PaymentService;
import com.projectteamspring.www.service.ShopService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/shop/*")
@Controller
public class ShopController {
	
	@Inject
	private ShopService ssv;
	
	@Inject
	private TmtFileHandler th;
	
	@Inject
	private TmtSubFileHandler tsh;
	
	@Inject
	private ProductFileHandler ph;
	
	@Inject
	private PaymentService psv;
	
	@GetMapping("/register")
	private void register() {}
	
	@PostMapping("/register")
	public String register(ShopVO svo, @RequestParam(name="files", required = false)MultipartFile[] files, @RequestParam("tagName")List<String> tagName) {
		svo.setTagNameList(tagName);
		List<ProductFileVO> flist = new ArrayList<ProductFileVO>();
		if(files[0].getSize()>0) {
			flist = ph.ProductFiles(files);
			int isOk = ssv.insert(new ShopDTO(svo ,flist));
		}else {
			int isOk = ssv.insertNoFile(svo);
		}
		return "redirect:/shop/productList";
	}
	
	@GetMapping("/list")
	public String getList(ShopPagingVO spvo, Model m) {
		List<ShopVO> list = ssv.getList(spvo);
		m.addAttribute("list", list);
		int totalCount = ssv.getTotalCount(spvo);
		ShopPagingHandler sph = new ShopPagingHandler(spvo, totalCount);
		m.addAttribute("ph", sph);
		return "/shop/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("pno")long pno,Model m) {
		ShopVO svo = ssv.getSelectOne(pno);
		m.addAttribute("svo", svo);
		return "/shop/detail";
	}
	
	@GetMapping("/productList")
	public String getAdminList(ProductPagingVO ppvo, Model m) {
		List<ShopVO> list = ssv.getProductList(ppvo);
		m.addAttribute("list", list);
		int totalCount = ssv.getProductTotalCount(ppvo);
		log.info("totalCount >>>>>>>>>>>> " + totalCount);
		ProductPagingHandler pph = new ProductPagingHandler(ppvo, totalCount);
		m.addAttribute("ph", pph);
		return "/shop/productList";
	}
	
	@GetMapping("/modify")
	public String modifyView(@RequestParam("pno")long pno, Model m) {
		ShopVO svo = ssv.getSelectOne(pno);
		m.addAttribute("svo", svo);
		return "/shop/modify";
	}
	
	@PostMapping("/modify")
	public String modify(ShopVO svo, @RequestParam(name="files", required = false)MultipartFile[] files, @RequestParam("tagName")List<String> tagName) {
		List<ProductFileVO> flist = new ArrayList<ProductFileVO>();
		svo.setTagNameList(tagName);
		if(files[0].getSize()>0) {
			flist = ph.ProductFiles(files);
			int isOk = ssv.incModify(new ShopDTO(svo, flist));
		}else if(files[0].getSize()==0 && files[1].getSize()>0) {
			flist = ph.ProductFiles(files);
			int isOk = ssv.excModify(new ShopDTO(svo, flist));
		}else {
			int isOk = ssv.noFileModify(svo);
		}
		return "redirect:/shop/productList";
	}
	
	@GetMapping("/mywish")
	public String mywish(String email, Model m) {
		List<MyWishVO> list = ssv.selectMyWishList(email);
		m.addAttribute("list", list);
		return "/shop/mywish";
	}
	
	@GetMapping("/myCart")
	public String myCart(String email, Model m) {
		List<StorageCartVO> list = ssv.selectMyCartList(email);
		m.addAttribute("list", list);
		List<PayMentVO> payList = psv.selectMyPayment(email);
		m.addAttribute("payList",payList);
		return "/shop/myCart";
	}
	
	@DeleteMapping(value="deleteMyCartList", consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteMyCartList(@RequestBody StorageCartVO scvo){
		log.info("scvo >>>>>>>>>>>> " + scvo);
		int isOk = ssv.deleteMyCartList(scvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/searchMyWish", consumes = "application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> myWish(@RequestBody MyWishVO mwvo){
		int isOk = ssv.searchMyWish(mwvo);
		return new ResponseEntity<Integer>(isOk, HttpStatus.OK);
	}
	
	@PostMapping(value="/addMyWish", consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addMyWish(@RequestBody MyWishVO mwvo){
		int isOk = ssv.addMyWish(mwvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/removeMyWish", consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeMyWish(@RequestBody MyWishVO mwvo){
		int isOk = ssv.removeMyWish(mwvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/editMyCart", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> editMyCart(@RequestBody StorageCartVO scart){
		int isOk = ssv.editStorageCart(scart);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/editBuyCart", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> editBuyCart(@RequestBody MyCartVO mcvo){
		int isOk = ssv.editBuyCart(mcvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="deleteMyWish/{pno}/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> deleteMyWish(@PathVariable("pno")long pno, @PathVariable("email")String email){
		int isOk = ssv.deleteMyWish(pno, email);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value="/selectList", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<MyCartVO>> selectList(@RequestBody MyCartVO mcvo){
		String email = mcvo.getEmail();
		List<MyCartVO> newmcvo = ssv.selectMCVO(email);
		return new ResponseEntity<List<MyCartVO>>(newmcvo, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteCart/{ctno}", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> deleteCart(@PathVariable("ctno")long ctno){
		int isOk = ssv.deleteCart(ctno);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//main image 저장
	@PostMapping(value="/tmtfile", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> tmtFileSave(@RequestParam(name="file")MultipartFile file){
		TmtFileSaveVO tvo =  th.tmtFile(file);
		int isOk = ssv.tmtporary(tvo);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//main image 경로 갖고오기
	@GetMapping(value="/getImagePath", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getImagePath(){
		String imagePath = ssv.getImage();
		return new ResponseEntity<String>(imagePath, HttpStatus.OK);
	}
	//main image uuid 갖고오기
	@GetMapping(value="/loadTmtUuid", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> loadTmtUuid(){
		String uuid = ssv.getUuid();
		return new ResponseEntity<String>(uuid, HttpStatus.OK);
	}
	//main image 파일이름 갖고오기
	@GetMapping(value="/loadtmtFileName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loadtmtFileName(){
		String fileName = ssv.getFileName();
		return new ResponseEntity<String>(fileName, HttpStatus.OK);
	}
	//sub image 저장
	@PostMapping(value="/subFileUpload", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> subFileUpload(@RequestParam(name="files", required = false)MultipartFile[] files){
		List<TmtFileSaveVO> flist = new ArrayList<>();
		flist = tsh.subFiles(files);
		int isOk = ssv.tmtSubporary(flist);
		
		return isOk>0? new ResponseEntity<String>(String.valueOf(isOk),HttpStatus.OK) :
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//sub image 경로 갖고오기
	@GetMapping(value="/loadTmtSubFile/{num}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> loadTmtSubFile(@PathVariable("num")int num){
		List<String> subImagePath = ssv.getSubImage(num);
		return new ResponseEntity<List<String>>(subImagePath, HttpStatus.OK);
	}
	//sub image uuid 갖고오기
	@GetMapping(value="/loadTmtSubUuid/{num}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> loadTmtSubUuid(@PathVariable("num")int num){
	List<String> subUuid = ssv.getsubUuid(num);
		return new ResponseEntity<List<String>>(subUuid, HttpStatus.OK);
	}
	//sub image 파일이름 갖고오기
	@GetMapping(value="/loadtmtSubFileName/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> loadtmtSubFileName(@PathVariable("num")int num){
		List<String> subFileName = ssv.getsubFileName(num);
		return new ResponseEntity<List<String>>(subFileName, HttpStatus.OK);
	}
	
	//기존 파일들 삭제
	@PutMapping(value="/prevFilemodify", consumes = "application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deletePrevFile(@RequestBody String uuid){
		String newuuid = uuid.substring(1,uuid.lastIndexOf("\""));
		int isOk = ssv.deletePrevFile(newuuid);
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/cancleFileDel")
	public ResponseEntity<String> cancleFileDel(){
		log.info("여기 오긴함?");
		int isOk = ssv.cancelDelFile();
		log.info("isOk >>>>>>>>>>> " + isOk);
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//product 삭제
	@GetMapping(value="/deleteProduct/{pno}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteProduct(@PathVariable("pno")long pno){
		int isOk = ssv.deleteProduct(pno);
		return isOk>0? new ResponseEntity<String>("1",HttpStatus.OK) : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
