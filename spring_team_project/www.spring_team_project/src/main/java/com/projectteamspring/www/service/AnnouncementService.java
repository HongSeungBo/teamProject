package com.projectteamspring.www.service;

import java.util.List;

import com.projectteamspring.www.domain.AnnouncementVO;
import com.projectteamspring.www.domain.BoardPagingVO;

public interface AnnouncementService {

	int insert(AnnouncementVO avo);

	List<AnnouncementVO> getList(BoardPagingVO pgvo);

	int getTotalCount(BoardPagingVO pgvo);

	AnnouncementVO getDetail(long ano);

	int modify(AnnouncementVO avo);

	int remove(long ano);

	List<AnnouncementVO> getIndexList();

}
