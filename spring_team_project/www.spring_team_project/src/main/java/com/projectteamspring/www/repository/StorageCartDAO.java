package com.projectteamspring.www.repository;

import java.util.List;

import com.projectteamspring.www.domain.PayMentVO;
import com.projectteamspring.www.domain.StorageCartVO;

public interface StorageCartDAO {

	int insertInfo(StorageCartVO scart);

	List<StorageCartVO> selectMyCartList(String email);

	int buyUp(PayMentVO pmvo);

	void deleteOneWeek(String email);

	int deleteMyCartList(StorageCartVO scvo);

}
