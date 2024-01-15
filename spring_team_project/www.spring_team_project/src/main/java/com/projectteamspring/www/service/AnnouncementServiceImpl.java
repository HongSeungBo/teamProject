package com.projectteamspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.projectteamspring.www.domain.AnnouncementVO;
import com.projectteamspring.www.domain.BoardPagingVO;
import com.projectteamspring.www.repository.AnnouncementDAO;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{

	@Inject
	private AnnouncementDAO adao;

	@Override
	public int insert(AnnouncementVO avo) {
		
		return adao.insert(avo);
	}

	@Override
	public List<AnnouncementVO> getList(BoardPagingVO pgvo) {
		// TODO Auto-generated method stub
		return adao.selectList(pgvo);
	}

	@Override
	public int getTotalCount(BoardPagingVO pgvo) {
		
		return adao.totalCount(pgvo);
	}

	@Override
	public AnnouncementVO getDetail(long ano) {
		adao.readCount(ano,1);
		return adao.getDetail(ano);
	}

	@Override
	public int modify(AnnouncementVO avo) {
		adao.readCount(avo.getAno(),-2);
		return adao.modify(avo);
	}

	@Override
	public int remove(long ano) {
		
		return adao.remove(ano);
	}

	@Override
	public List<AnnouncementVO> getIndexList() {
		return adao.getIndexList();
	}
	
	
}
