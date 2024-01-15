package com.projectteamspring.www.service;



import java.util.List;

import com.projectteamspring.www.domain.MemberFileVO;
import com.projectteamspring.www.domain.MemberPagingVO;
import com.projectteamspring.www.security.AuthVO;
import com.projectteamspring.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);


	MemberVO getDetail(String email);

	int uploadPath(MemberFileVO mfvo);

	MemberFileVO getCheck();

	String selectPwd(MemberVO mvo);

	int remove(String email);

	void getPicOne(MemberFileVO mfvo);

	MemberFileVO getProFile(String email);

	int existsByEmail(String email);

	int checkEmail(String email);

	int getEmail(String email);

	void modifyFile(MemberFileVO mfvo);

	int registerDetail(MemberVO mvo);

	void realmodifyFile(MemberFileVO mfvo);

	int modify(MemberVO mvo);

	int totalmodify(MemberFileVO mfvo);

	List<MemberVO> getList(MemberPagingVO mgvo);

	List<MemberVO> getleaveList(MemberPagingVO mgvo);

	int getMemberCount(MemberPagingVO mgvo);

	int getLeaveMemberCount(MemberPagingVO mgvo);

	MemberVO getFindEmail(String email);

	int modifyPwd(String email, String pwd);

	


}
