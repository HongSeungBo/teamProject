package com.projectteamspring.www.repository;



import java.util.List;

import com.projectteamspring.www.domain.MemberFileVO;

public interface MemberFileDAO {

	int uploadPath(MemberFileVO mfvo);

	MemberFileVO selectProfile();

	List<MemberFileVO> selectAll();

	void allDeleteFile();

	
}
