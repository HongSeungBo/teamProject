package com.projectteamspring.www.service;

import java.util.List;

import com.projectteamspring.www.domain.MapDTO;
import com.projectteamspring.www.domain.MapFileVO;
import com.projectteamspring.www.domain.MapVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;

public interface MapService {

	int insertMarker(MapVO mvo);

	List<MapVO> getMarkerList();

	List<MapVO> getSearchMarkerList(MapVO mvo);

	MapDTO getClickMarker(long mno);

	long setDescMarker();

	int tmtMarkerPorary(List<TmtFileSaveVO> flist);

	int insertFiles(List<MapFileVO> flist);

	int markerModifyMno(MapVO mvo);

	MapFileVO getFile(String uuid);

	int markerFileDeleteAll(long mno);

	int markerDelete(long mno);

	List<MapVO> indexMarker(int cnt);


}
