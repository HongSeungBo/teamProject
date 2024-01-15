package com.projectteamspring.www.service;

import java.util.List;




import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.domain.BoardVO;

public interface BoardService {


	List<BoardVO> getList(BoardPagingVO boardPagingVO);

	int getTotalCount(BoardPagingVO boardPagingVO);

	BoardVO getDetail(long bno);

	int insert(BoardVO bvo);

	int modify(BoardVO bvo);

	int remove(long bno);

	List<BoardVO> selectTop();

	List<BoardVO> topList();

	List<BoardVO> getMyList(String authEmail);

	

	

	

}
