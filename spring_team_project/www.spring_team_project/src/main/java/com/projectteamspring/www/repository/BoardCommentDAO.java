package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.BoardCommentVO;
import com.projectteamspring.www.domain.BoardPagingVO;

public interface BoardCommentDAO {

	int insert(BoardCommentVO bcvo);

	int selectOneBnoTotalCount(long bno);

	List<BoardCommentVO> selectListPaging(@Param("bno")long bno,@Param("bpgvo") BoardPagingVO bpgvo);

	int delete(long cno);

	int update(BoardCommentVO bcvo);

	void deleteAll(long bno);

}
