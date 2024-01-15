package com.projectteamspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;


import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.domain.BoardVO;
import com.projectteamspring.www.repository.BoardCommentDAO;
import com.projectteamspring.www.repository.BoardDAO;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;

	@Inject
	private BoardCommentDAO bcdao;
	

	@Override
	public List<BoardVO> getList(BoardPagingVO boardPagingVO) {
		// TODO Auto-generated method stub
		bdao.commentCnt();
		return bdao.selectList(boardPagingVO);
	}

	@Override
	public int getTotalCount(BoardPagingVO boardPagingVO) {
		// TODO Auto-generated method stub
		return bdao.getTotalList(boardPagingVO);
	}


	@Override
	public int insert(BoardVO bvo) {
		
		return bdao.insert(bvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		bdao.readCount(bno,1);
		return bdao.detail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		bdao.readCount(bvo.getBno(),-2);
		return bdao.modify(bvo);
	}

	@Override
	public int remove(long bno) {
		
		bcdao.deleteAll(bno);
		return bdao.remove(bno);
	}

	@Override
	public List<BoardVO> selectTop() {
		// TODO Auto-generated method stub
		return bdao.selectTop();
	}

	@Override
	public List<BoardVO> topList() {
		bdao.commentCnt();
		return bdao.topList();
	}

	@Override
	public List<BoardVO> getMyList(String authEmail) {
		// TODO Auto-generated method stub
		return bdao.myList(authEmail);
	}


	

}
