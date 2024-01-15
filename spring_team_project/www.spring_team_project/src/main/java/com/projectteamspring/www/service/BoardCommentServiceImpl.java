package com.projectteamspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.projectteamspring.www.domain.BoardCommentVO;
import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.handler.BoardPagingHandler;
import com.projectteamspring.www.repository.BoardCommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardCommentServiceImpl implements BoardCommentService{

	@Inject
	private BoardCommentDAO bcdao;

	@Override
	public int insert(BoardCommentVO bcvo) {
		// TODO Auto-generated method stub
		return bcdao.insert(bcvo);
	}

	@Override
	public BoardPagingHandler getList(long bno, BoardPagingVO bpgvo) {
		int totalCount = bcdao.selectOneBnoTotalCount(bno);
		
		List<BoardCommentVO> list = bcdao.selectListPaging(bno, bpgvo);
		BoardPagingHandler bph = new BoardPagingHandler(bpgvo, totalCount, list);
		return bph;
	}

	@Override
	public int delete(int cno) {
		// TODO Auto-generated method stub
		return bcdao.delete(cno);
	}

	@Override
	public int update(BoardCommentVO bcvo) {
		// TODO Auto-generated method stub
		return bcdao.update(bcvo);
	}
	
}
