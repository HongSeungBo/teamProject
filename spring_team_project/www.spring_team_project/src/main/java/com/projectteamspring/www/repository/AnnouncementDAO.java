package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.AnnouncementVO;
import com.projectteamspring.www.domain.BoardPagingVO;

public interface AnnouncementDAO {

	int insert(AnnouncementVO avo);

	List<AnnouncementVO> selectList(BoardPagingVO pgvo);

	int totalCount(BoardPagingVO pgvo);

	AnnouncementVO getDetail(long ano);

	int modify(AnnouncementVO avo);

	int remove(long ano);

	void readCount(@Param("ano") long ano, @Param("cnt")int i);

	List<AnnouncementVO> getIndexList();

}
