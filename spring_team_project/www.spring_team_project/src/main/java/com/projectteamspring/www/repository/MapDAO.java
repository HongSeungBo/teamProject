package com.projectteamspring.www.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.MapFileVO;
import com.projectteamspring.www.domain.MapVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;

public interface MapDAO {

	int insertMarker(MapVO mvo);

	List<MapVO> getMarkerList();

	List<MapVO> getSearchMarkerList(MapVO mvo);

	MapVO getClickMarker(long mno);

	long setDescMarker();

	int tmtMarkerPorary(TmtFileSaveVO tvo);

	int insertFiles(MapFileVO mfvo);

	List<MapFileVO> getClickMarkerFiles(long mno);

	int markerModifyMno(MapVO mvo);

	MapFileVO getFile(String uuid);

	int markerFileDeleteAll(long mno);

	int markerDelete(long mno);

	List<MapVO> indexMarker(int cnt);


}
