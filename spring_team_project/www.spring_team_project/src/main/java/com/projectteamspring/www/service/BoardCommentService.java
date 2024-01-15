package com.projectteamspring.www.service;

import com.projectteamspring.www.domain.BoardCommentVO;
import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.handler.BoardPagingHandler;

public interface BoardCommentService {

	int insert(BoardCommentVO bcvo);

	BoardPagingHandler getList(long bno, BoardPagingVO bpgvo);

	int delete(int cno);

	int update(BoardCommentVO bcvo);

}
