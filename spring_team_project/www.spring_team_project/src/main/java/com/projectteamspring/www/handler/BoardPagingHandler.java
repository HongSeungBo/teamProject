package com.projectteamspring.www.handler;

import java.util.List;

import com.projectteamspring.www.domain.BoardCommentVO;
import com.projectteamspring.www.domain.BoardPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardPagingHandler {
	
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev, next;
	
	private int totalCount;
	private BoardPagingVO pgvo;
	
	private List<BoardCommentVO> cmtList;
	
	public BoardPagingHandler(BoardPagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = 
				(int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty())*pgvo.getQty();
		
		this.startPage = endPage -9 ;
		
		this.realEndPage =
				(int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if (realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
	public BoardPagingHandler(BoardPagingVO bpgvo, int totalCount, List<BoardCommentVO> cmtList) {
		this(bpgvo,totalCount);
		this.cmtList = cmtList;
	}
}
