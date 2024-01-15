package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.MyWishVO;

public interface MyWishDAO {

	int selectEmail(MyWishVO mwvo);

	int insertInfo(MyWishVO mwvo);

	int removeInfo(MyWishVO mwvo);

	List<MyWishVO> selectMyWishList(String email);

	int delete(@Param("pno")long pno, @Param("email")String email);

	void deleteOneWeek(String email);

}
