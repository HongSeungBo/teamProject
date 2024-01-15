package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.MemberPagingVO;
import com.projectteamspring.www.security.AuthVO;
import com.projectteamspring.www.security.MemberVO;

public interface MemberDAO {

	int register(MemberVO mvo);

	int registerDetail(MemberVO mvo);
	
	int insertAuthInit(String email);

	MemberVO selectEmail(String username);
	
	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	MemberVO selectUser(String email);

	String selectPwd(MemberVO mvo);

	int modify(MemberVO mvo);

	int remove(String email);

	int existsByEamil(String email);

	int getEmail(String email);

	List<MemberVO> getList(MemberPagingVO mgvo);

	List<MemberVO> leaveList(MemberPagingVO mgvo);

	int getMemberCount(MemberPagingVO mgvo);

	int getLeaveMembercount(MemberPagingVO mgvo);

	MemberVO findEmail(String email);

	int modifyPwd(@Param("email") String email, @Param("pwd") String pwd);

	


}
