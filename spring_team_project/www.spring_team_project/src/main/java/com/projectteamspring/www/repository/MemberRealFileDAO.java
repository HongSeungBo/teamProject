package com.projectteamspring.www.repository;

import java.util.List;

import com.projectteamspring.www.domain.MemberFileVO;

public interface MemberRealFileDAO {

	void insert(MemberFileVO mfvo);

	MemberFileVO getProFile(String email);

	int modify(MemberFileVO mfvo);

	int checkEmail(String email);

	void realmodifyfile(MemberFileVO mfvo);

	List<MemberFileVO> selectAll();

	

}
