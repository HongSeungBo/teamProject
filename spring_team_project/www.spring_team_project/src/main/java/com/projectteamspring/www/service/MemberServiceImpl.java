package com.projectteamspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectteamspring.www.domain.MemberFileVO;
import com.projectteamspring.www.domain.MemberPagingVO;
import com.projectteamspring.www.repository.MemberDAO;
import com.projectteamspring.www.repository.MemberFileDAO;
import com.projectteamspring.www.repository.MemberRealFileDAO;
import com.projectteamspring.www.security.AuthVO;
import com.projectteamspring.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

	@Inject
	private MemberDAO mdao;
	
	@Inject
	private MemberFileDAO mfdao;
	
	@Inject
	private MemberRealFileDAO mrfdao;
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.register(mvo);
		
		return mdao.insertAuthInit(mvo.getEmail());
	}
	@Override
	public int registerDetail(MemberVO mvo) {
		int isOk = mdao.registerDetail(mvo);
		return isOk;
	}
	
	@Override
	public boolean updateLastLogin(String authEmail) {
		
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}
	
	@Override
	public int getEmail(String email) {
		log.info("getEmail in MemberServiceImpl");
		return mdao.getEmail(email);
	}


	@Override
	public MemberVO getDetail(String email) {
		
		return mdao.selectUser(email);
	}

	@Override
	public int uploadPath(MemberFileVO mfvo) {
		
		return mfdao.uploadPath(mfvo);
	}

	@Override
	public MemberFileVO getCheck() {
		
		return mfdao.selectProfile();
	}

	@Override
	public String selectPwd(MemberVO mvo) {
		
		return mdao.selectPwd(mvo);
	}

	
	@Override
	public int modify(MemberVO mvo) {
		
		int isOk = mdao.modify(mvo);

		return isOk;
	}

	@Override
	public int remove(String email) {
		
		return mdao.remove(email);
	}

	@Override
	public void getPicOne(MemberFileVO mfvo) {
		mrfdao.insert(mfvo);
	}

	@Override
	public MemberFileVO getProFile(String email) {
		return mrfdao.getProFile(email);
	}

	@Override
	public void modifyFile(MemberFileVO mfvo) {
		
		mrfdao.modify(mfvo);
		
	}
	
	@Override
	public int checkEmail(String email) {
		
		return mrfdao.checkEmail(email);
	}

	@Override
	public int existsByEmail(String email) {
		
		return mdao.existsByEamil(email);
	}

	@Override
	public void realmodifyFile(MemberFileVO mfvo) {
		
		mrfdao.realmodifyfile(mfvo);
	}


	@Override
	public int totalmodify(MemberFileVO mfvo) {
		
		int isOk = mrfdao.modify(mfvo);
		return isOk;
	}
	@Override
	public List<MemberVO> getList(MemberPagingVO mgvo) {
		
		return mdao.getList(mgvo);
		
	}
	@Override
	public List<MemberVO> getleaveList(MemberPagingVO mgvo) {
		// TODO Auto-generated method stub
		return mdao.leaveList(mgvo);
	}
	@Override
	public int getMemberCount(MemberPagingVO mgvo) {
		// TODO Auto-generated method stub
		return mdao.getMemberCount(mgvo);
	}
	@Override
	public int getLeaveMemberCount(MemberPagingVO mgvo) {
		// TODO Auto-generated method stub
		return mdao.getLeaveMembercount(mgvo);
	}
	@Override
	public MemberVO getFindEmail(String email) {
		// TODO Auto-generated method stub
		return mdao.findEmail(email);
	}
	@Override
	public int modifyPwd(String email, String pwd) {
		// TODO Auto-generated method stub
		return mdao.modifyPwd(email,pwd);
	}

	
	
	

	
	
}
