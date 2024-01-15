package com.projectteamspring.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.projectteamspring.www.domain.MapDTO;
import com.projectteamspring.www.domain.MapFileVO;
import com.projectteamspring.www.domain.MapVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;
import com.projectteamspring.www.repository.MapDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MapServiceImpl implements MapService {

	@Inject
	private MapDAO mdao;

	@Override
	public int insertMarker(MapVO mvo) {
		log.info("insertMarker in MapServiceImpl");
		return mdao.insertMarker(mvo);
	}

	@Override
	public List<MapVO> getMarkerList() {
		log.info("getMarkerList in MapServiceImpl");
		return mdao.getMarkerList();
	}

	@Override
	public List<MapVO> getSearchMarkerList(MapVO mvo) {
		log.info("getSearchMarkerList in MapServiceImpl");
		return mdao.getSearchMarkerList(mvo);
	}

	@Override
	public MapDTO getClickMarker(long mno) {
		log.info("getClickMarker in MapServiceImpl");
		MapDTO mdto = new MapDTO();
		mdto.setMvo(mdao.getClickMarker(mno));
		List<MapFileVO> flist = new ArrayList<MapFileVO>();
		flist=(mdao.getClickMarkerFiles(mno));
		mdto.setFiles(flist);
		return mdto;
	}

	@Override
	public long setDescMarker() {
		return mdao.setDescMarker();
	}

	@Override
	public int tmtMarkerPorary(List<TmtFileSaveVO> flist) {
		int isok=1;
		for(TmtFileSaveVO tvo : flist) {
			isok*= mdao.tmtMarkerPorary(tvo);
		}
		return isok;
	}

	@Override
	public int insertFiles(List<MapFileVO> flist) {
		int isOK = 1;
		for(MapFileVO mfvo : flist) {
			log.info(" <><><<<<>>><<>><>< "+mfvo);
			isOK *= mdao.insertFiles(mfvo);
		}
		return isOK;
	}

	@Override
	public int markerModifyMno(MapVO mvo) {
		log.info("markerModifyMno in MapServiceImpl");
		return mdao.markerModifyMno(mvo);
	}

	@Override
	public MapFileVO getFile(String uuid) {
		log.info("getFile in MapServiceImpl");
		return mdao.getFile(uuid);
	}

	@Override
	public int markerFileDeleteAll(long mno) {
		log.info("markerFileDeleteAll in MapServiceImpl");
		return mdao.markerFileDeleteAll(mno);
	}

	@Override
	public int markerDelete(long mno) {
		log.info("markerDelete in MapServiceImpl");
		return mdao.markerDelete(mno);
	}

	@Override
	public List<MapVO> indexMarker(int cnt) {
		log.info("indexMarker in MapServiceImpl");
		
		return mdao.indexMarker(cnt);
	}
}
