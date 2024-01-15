package com.projectteamspring.www.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectteamspring.www.domain.BoardCommentVO;
import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.handler.BoardPagingHandler;
import com.projectteamspring.www.service.BoardCommentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment/*")
@Slf4j
public class BoardCommentController {

	@Inject
	private BoardCommentService bcsv;
	
	@PostMapping(value = "/post", consumes = "application/json",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody BoardCommentVO bcvo){
		int isOk = bcsv.insert(bcvo);
		return isOk> 0 ? new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BoardPagingHandler> spread(@PathVariable("bno")long bno,
			@PathVariable("page")int page){
		log.info("bno>> page >>"+bno, page);
		BoardPagingVO bpgvo = new BoardPagingVO(page, 5);
		log.info("pageNo"+bpgvo.getPageNo());
		return new ResponseEntity<BoardPagingHandler>(bcsv.getList(bno,bpgvo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{cno}")
	public ResponseEntity<String> delete(@PathVariable("cno")int cno){
		log.info("cno>>>"+cno);
		int isOk = bcsv.delete(cno);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/{cno}", consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> update(@PathVariable("cno")long cno, @RequestBody BoardCommentVO bcvo){
		log.info("cno>>>"+bcvo.getCno());
		int isOk = bcsv.update(bcvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK):
			new ResponseEntity<String>("2", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
