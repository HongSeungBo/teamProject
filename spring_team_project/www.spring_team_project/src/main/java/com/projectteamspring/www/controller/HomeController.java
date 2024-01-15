package com.projectteamspring.www.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectteamspring.www.domain.AnnouncementVO;
import com.projectteamspring.www.domain.BoardVO;
import com.projectteamspring.www.domain.MemberFileVO;
import com.projectteamspring.www.domain.ShopVO;
import com.projectteamspring.www.security.MemberVO;
import com.projectteamspring.www.service.AnnouncementService;
import com.projectteamspring.www.service.BoardService;
import com.projectteamspring.www.service.MemberService;
import com.projectteamspring.www.service.ShopService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	@Inject
	private MemberService msv;
	@Inject
	private ShopService ssv;
	
	@Inject
	private BoardService bsv;
	
	@Inject
	private AnnouncementService asv; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Authentication authentication) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<ShopVO> list = ssv.getTopWishList();
		model.addAttribute("list", list);
		
		List<BoardVO> topList = bsv.topList();
		model.addAttribute("topList", topList);
		
		List<AnnouncementVO> noticeList = asv.getIndexList();
		model.addAttribute("noticeList", noticeList);
		
		return "index";
	}
	
}
