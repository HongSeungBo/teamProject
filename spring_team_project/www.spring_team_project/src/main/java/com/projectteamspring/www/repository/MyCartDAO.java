package com.projectteamspring.www.repository;

import java.util.List;

import com.projectteamspring.www.domain.MyCartVO;
import com.projectteamspring.www.domain.PayMentVO;

public interface MyCartDAO {

	int editBuyCart(MyCartVO mcvo);

	List<MyCartVO> selectMCVO(String email);

	int delete(long ctno);

	int buyUp(PayMentVO pmvo);

	void deleteOneWeek(String email);
	
}
