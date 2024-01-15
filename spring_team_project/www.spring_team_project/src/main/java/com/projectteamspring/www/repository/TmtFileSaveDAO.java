package com.projectteamspring.www.repository;

import java.util.List;

import com.projectteamspring.www.domain.TmtFileSaveVO;

public interface TmtFileSaveDAO {

	int tmtporary(TmtFileSaveVO tvo);

	String getMaxImage();

	String getUuid();

	String getFileName();

	int tmtSubporary(TmtFileSaveVO tvo);

	List<String> getSubImage(int num);

	List<String> getSubUuid(int num);

	List<String> getSubFileName(int num);

	List<TmtFileSaveVO> selectListMainAllFiles();

	int deleteAllFiles();

	
}
