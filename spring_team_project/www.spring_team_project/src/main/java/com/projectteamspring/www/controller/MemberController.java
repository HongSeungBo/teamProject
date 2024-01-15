package com.projectteamspring.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectteamspring.www.domain.MemberFileVO;
import com.projectteamspring.www.domain.MemberPagingVO;
import com.projectteamspring.www.handler.MemberFileHandler;

import com.projectteamspring.www.handler.MemberPagingHandler;
import com.projectteamspring.www.security.AuthVO;

import com.projectteamspring.www.security.MemberVO;
import com.projectteamspring.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member/**")
@Controller
@Slf4j
public class MemberController {

	private static final String UPLOAD_DIR = "D:\\_myweb\\_java\\fileupload\\profile";
	
	@Inject
	private BCryptPasswordEncoder bcEncoder;
	
	@Inject
	private MemberService msv;
	
	@Inject
	private MemberFileHandler mfh;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")

	public String register(MemberVO mvo, @RequestParam("file") MultipartFile file, Model m) {
		

		log.info("register member");

		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		mvo.setAddress(mvo.getRegion()+" "+mvo.getSubRegion()+" "+mvo.getDetailaddress());
		mvo.setBirth(mvo.getBirthYear()+mvo.getBirthMonth()+mvo.getBirthDay());
		mvo.setPhonNum(mvo.getPhon1()+mvo.getPhon2()+mvo.getPhon3());
		
		int isOk = msv.register(mvo);
		
		MemberFileVO mfvo = new MemberFileVO();
		if (file.getSize() > 0) {
			mfvo=mfh.MemberFile(file);
			mfvo.setEmail(mvo.getEmail());
		 msv.getPicOne(mfvo);
		}
		
		return "redirect:/member/login";

	}
	
	 	@GetMapping("/checkEmail/{email}")
	    public ResponseEntity<String> checkEamil(@PathVariable String email) {
	       
	        int count = msv.existsByEmail(email);
	        log.info(email);
	        log.info("count"+count);
	        
	        return count == 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
	        	new ResponseEntity<String>("2", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	
	@PostMapping(value = "/image", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> register(@RequestParam("file") MultipartFile file) {
		log.info("file name > " + file.getOriginalFilename());
		MemberFileVO mfvo = new MemberFileVO();
		if (file.getSize() > 0) {
			mfvo =mfh.MemberFile(file);
		}
		log.info("flist >>> " + mfvo);
		int isOk = msv.uploadPath(mfvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("2", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MemberFileVO> check(){
		MemberFileVO mfvo = msv.getCheck();
		log.info("mfvo >>>>>> " + mfvo);
		return new ResponseEntity<MemberFileVO>(mfvo, HttpStatus.OK);
	}
	

	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}

	@GetMapping("/loginpage")
	public void loginpage() {}

	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		log.info("login member");
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return "redirect:/member/login";
	}
	
	@GetMapping(value="/loginHeader", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MemberFileVO> headerImg(  Authentication authentication) {
		if (authentication != null) {
			String email = authentication.getName();
			log.info(">>>>> email >> {} ",email);
			MemberFileVO mfvo = msv.getProFile(email);

			return new ResponseEntity<MemberFileVO>(mfvo, HttpStatus.OK);
		}else {
			MemberFileVO mfvo = null;
			return new ResponseEntity<MemberFileVO>(mfvo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("email")String email) {
		MemberVO mvo = msv.getDetail(email);
		String phon2 = mvo.getPhonNum().substring(3,7);
		String phon3 = mvo.getPhonNum().substring(7,11);
		log.info("nljasflknjdgslkn"+phon2+"assa"+phon3);
		m.addAttribute("phon2", phon2);
		m.addAttribute("phon3", phon3);
		m.addAttribute("mvo", mvo);
		
		String addressString = mvo.getAddress();
		String[] addressArray = addressString.split(" ");
		m.addAttribute("region", addressArray[0]);
		m.addAttribute("subRegion", addressArray[1]);
		m.addAttribute("detailAddress", addressArray[2]);
		
		String[] birth = mvo.getBirth().split("-");
		
		m.addAttribute("birthyear", birth[0]);
		m.addAttribute("birthmonth", birth[1]);
		m.addAttribute("birthday", birth[2]);
		
		String nickName =  mvo.getNickName();
		m.addAttribute("NickName", nickName);
		
		MemberFileVO mfvo = msv.getProFile(email);
		m.addAttribute("mfvo", mfvo);
		
		
	}

	@GetMapping(value = "/getEmail/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmail(@PathVariable("email") String email){
		log.info(" <><>><><><> "+email);
		int isOK = msv.getEmail(email);
		log.info(" <><>><><><> "+isOK);
		return isOK>0 ?
				new ResponseEntity<String>("1",HttpStatus.OK):
				new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/naverLogin")
	public void naverLogin() {}
	
	@GetMapping("/NaverRegister")
	public String NaverRegister(MemberVO mvo,Model m) {
		
		mvo.setPlatform("N");
		m.addAttribute("mvo",mvo);
		
		
		return "/member/register";
	}
	
	@GetMapping("/KakaoRegister")
	public String KakaoRegister(MemberVO mvo,Model m) {
		
		mvo.setPlatform("K");
		m.addAttribute("mvo",mvo);
		
		
		return "/member/register";
	}
	
	@GetMapping("/goologin")
	public void goologin() {}

	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, Model m, RedirectAttributes re, HttpServletResponse res,	HttpServletRequest req,
			@RequestParam("file") MultipartFile file) {
		

		if (mvo.getPwd().isEmpty()) {
			String NoPwd = msv.selectPwd(mvo);
			mvo.setPwd(NoPwd);
		}else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		}
		

		mvo.setAddress(mvo.getRegion()+" "+mvo.getSubRegion()+" "+mvo.getDetailaddress());
		log.info("getdetail address >>"+mvo.getDetailaddress());
		mvo.setBirth(mvo.getBirthYear()+"-"+mvo.getBirthMonth()+"-"+mvo.getBirthDay());
		mvo.setPhonNum(mvo.getPhon1()+mvo.getPhon2()+mvo.getPhon3());
		
		MemberFileVO mfvo = new MemberFileVO();
		
		
		int isOk = msv.modify(mvo);
		
		
		log.info("getEmail"+mvo.getEmail());
		
		if (file.getSize() > 0) {

			int count = msv.checkEmail(mvo.getEmail());
			log.info("count >> " + count);
			if (count == 0) {
				mfvo=mfh.MemberFile(file);
				mfvo.setEmail(mvo.getEmail());
				int isTotalOk = msv.totalmodify(mfvo);
				logout(req, res);
				
			}else {
				
				mfvo=mfh.MemberFile(file);
				mfvo.setEmail(mvo.getEmail());
				msv.realmodifyFile(mfvo);
				log.info("count > 0 getEmail"+mvo.getEmail());
				logout(req, res);
			}
			
			
		}
		
		return "/member/login";
		
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("email")String email,RedirectAttributes re,
			HttpServletRequest req, HttpServletResponse res) {
		int isOk = msv.remove(email);
		re.addFlashAttribute("isOk", isOk);
		logout(req, res);
		return "redirect:/";
	}
	
	@GetMapping("list")
	public String memberList(Model m, MemberPagingVO mgvo) {
		
		List<MemberVO> mlist = msv.getList(mgvo);
		m.addAttribute("mlist", mlist);
		int count = msv.getMemberCount(mgvo);
		
		MemberPagingHandler mph = new MemberPagingHandler(mgvo, count);
		m.addAttribute("mph", mph);

		
		return "/member/list";
	}
	
	@GetMapping("leaveList")
	public String leaveList(Model m, MemberPagingVO mgvo) {
		List<MemberVO> leaveList = msv.getleaveList(mgvo);
		m.addAttribute("leaveList", leaveList);
		
		int count = msv.getLeaveMemberCount(mgvo);
		
		MemberPagingHandler mph = new MemberPagingHandler(mgvo, count);
		m.addAttribute("mph", mph);
		return "/member/leaveList";
	}
	
	@GetMapping("FindMvoInfo")
	public String findInfo() {
		return "/member/FindMvoInfo"; 
	}
	
	@PostMapping("/FindMvoInfo")
	public String findInfo(@RequestParam("email")String email, @RequestParam("phon1")String phon1,
			@RequestParam("phon2")String phon2 ,@RequestParam("phon3")String phon3, 
			Model m) {
		
		MemberVO find = msv.getFindEmail(email);
		log.info(find.getPhonNum());
		String phon = phon1+phon2+phon3;
		
		log.info("전화번호"+phon);
		
		log.info("Email >> "+email);
		log.info("findEmail >> "+find);
		
		
		if (find != null && find.getPhonNum().equals(phon)) {
			
			log.info(find.getEmail());
			log.info(find.getName());
			log.info(find.getAddress());
			m.addAttribute("mvo", find);
			
			return "/member/changePwd";
		}else {
			m.addAttribute("errMsg", "이메일과 전화번호가 일치하지 않습니다");
		}
		return "/member/FindMvoInfo";
	}
	
	@PostMapping("/modifyPwd")
	public String modifyPwd(@RequestParam("email") String email, @RequestParam("pwd")String pwd,
			@RequestParam("pwd2") String pwd2, RedirectAttributes re, Model m) {
		log.info("pwd2 >>"+pwd2);
		log.info("pwd >>"+pwd);
		
		if (pwd.equals(pwd2)) {
			pwd = bcEncoder.encode(pwd);
			int modify = msv.modifyPwd(email,pwd);
			re.addFlashAttribute("modify", modify);
			return "/member/login";
		}else {
			m.addAttribute("errMsg", "비밀번호가 일치하지 않습니다");
		}
		return "/member/changePwd";
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse res	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req, res, authentication);
	}
}
	