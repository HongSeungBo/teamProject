package com.projectteamspring.www.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
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


import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.domain.BoardVO;
import com.projectteamspring.www.handler.BoardPagingHandler;
import com.projectteamspring.www.security.MemberVO;
import com.projectteamspring.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	 private static final String UPLOAD_DIR = "D:\\_myweb\\_java\\fileupload\\boardfile";
	
	
	
	@Inject
	private BoardService bsv;

	@GetMapping("/register")
	public String register() {
		log.info("start >>");
		return "/board/register";
	}
	
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes re) {
		log.info(bvo.toString());
		int isUp = bsv.insert(bvo);
		re.addFlashAttribute("isUp", isUp);
		return "redirect:/board/list";
	}
	
	@PostMapping("/image")
    public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) {
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

            // 이미지의 상대 경로를 반환 (예: /upload/filename.jpg)
            String relativePath = "/upload/boardfile/" + urlEncodedFileName;

            return ResponseEntity.ok(relativePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }
	

	@GetMapping("/list")
	public String list(Model m, BoardPagingVO boardPagingVO) {
		log.info(boardPagingVO.toString());
		List<BoardVO> list = bsv.getList(boardPagingVO);
		m.addAttribute("list", list);
		int totalCount = bsv.getTotalCount(boardPagingVO);
		log.info(">> pagingVO >> "+ boardPagingVO);
		BoardPagingHandler bph = new BoardPagingHandler(boardPagingVO, totalCount);
		m.addAttribute("bph", bph);
		return "/board/list";
	}
	
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m , @RequestParam("bno")long bno){
		BoardVO bvo = bsv.getDetail(bno);


		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo,RedirectAttributes reAttr) {
		int isOk = bsv.modify(bvo);
		reAttr.addFlashAttribute("isOk", isOk);
		reAttr.addAttribute("bno",bvo.getBno());
		return "redirect:/board/detail";
		
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
            String relativePath = "/upload/boardfile/" + urlEncodedFileName;

            return ResponseEntity.ok(relativePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")long bno, RedirectAttributes reAtte) {
		int isdel = bsv.remove(bno);
		
		reAtte.addFlashAttribute("isdel", isdel);
		return "redirect:/board/list";
	}
	
	
}
