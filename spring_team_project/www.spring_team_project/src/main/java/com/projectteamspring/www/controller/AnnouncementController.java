package com.projectteamspring.www.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projectteamspring.www.domain.AnnouncementVO;
import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.handler.BoardPagingHandler;
import com.projectteamspring.www.service.AnnouncementService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/announcement/*")
@Slf4j
public class AnnouncementController {

	private static final String UPLOAD_DIR = "D:\\_myweb\\_java\\fileupload\\announcement";
	
	@Inject
	private AnnouncementService asv;
	
	@GetMapping("/register")
	private void register() {}
	
	@PostMapping("/register")
	private String register(AnnouncementVO avo) {
		int isOk = asv.insert(avo);
		return "redirect:/announcement/list";
	}
	
	@PostMapping("/image")
	public ResponseEntity<String> ImageUpload(@RequestParam("file") MultipartFile file){
		try {
			String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String baseName = FilenameUtils.getBaseName(originalFileName);
            String extension = FilenameUtils.getExtension(originalFileName);
            
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            String uniqueFileName = UUID.randomUUID().toString() + "_" + baseName+"."+extension;
            String urlEncodedFileName = URLEncoder.encode(uniqueFileName, StandardCharsets.UTF_8.toString());
            
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath);
            
            String relativePath = "/upload/announcement/" + urlEncodedFileName;
            return ResponseEntity.ok(relativePath);
		} catch (Exception e) {
			 e.printStackTrace();
	            return ResponseEntity.status(500).body("Error uploading image");
		}
	}
	
	@GetMapping("list")
	public String list(Model m, BoardPagingVO pgvo) {
		List<AnnouncementVO> list = asv.getList(pgvo);
		m.addAttribute("list", list);
		int totalCount = asv.getTotalCount(pgvo);
		BoardPagingHandler bph = new BoardPagingHandler(pgvo, totalCount);
		m.addAttribute("bph", bph);
		
		return "/announcement/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m, @RequestParam("ano") long ano) {
		AnnouncementVO avo = asv.getDetail(ano);
		
		m.addAttribute("avo", avo);
	}
	
	@PostMapping("/modify")
	public String modify(AnnouncementVO avo, RedirectAttributes reAttr) {
		int isOk = asv.modify(avo);
		
		reAttr.addFlashAttribute("isOk", isOk);
		reAttr.addAttribute("ano", avo.getAno());
		
		return "redirect:/announcement/detail";
	}
	
	
	@PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam("file") MultipartFile file) {
        try {
            // 업로드된 파일의 원래 이름
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String baseName = FilenameUtils.getBaseName(originalFilename);
            String extension = FilenameUtils.getExtension(originalFilename);
            
            // 파일 저장 경로 설정
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String uniqueFileName = UUID.randomUUID().toString() + "_" + baseName+"."+extension;
            String urlEncodedFileName = URLEncoder.encode(uniqueFileName, StandardCharsets.UTF_8.toString());
            // 파일 저장
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            // 이미지의 상대 경로를 반환 /upload/filename.jpg)
            String relativePath = "/upload/announcement/" + urlEncodedFileName;

            return ResponseEntity.ok(relativePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }
	
	@GetMapping("/remove")
	public String remove(@RequestParam("ano")long ano, RedirectAttributes reAtte) {
		int isdel = asv.remove(ano);
		log.info(isdel+"isdelasdasdasd");
		reAtte.addFlashAttribute("isdel", isdel);
		return "redirect:/announcement/list"; 
	}
}
