package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.domain.BoardVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	long selectOneBno();

	List<BoardVO> selectList(BoardPagingVO boardPagingVO);

	int getTotalList(BoardPagingVO boardPagingVO);

//	BoardVO getDetail(long bno);

	int insert(BoardVO bvo);

	BoardVO detail(long bno);

	int modify(BoardVO bvo);

	int remove(long bno);

	void readCount(@Param("bno") long bno, @Param("cnt") int cnt);

	List<BoardVO> selectTop();

	List<BoardVO> topList();

	void commentCnt();

	List<BoardVO> myList(String authEmail);

	

}
