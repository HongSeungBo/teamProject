package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.ProductFileVO;

public interface ProductFileDAO {

	int insertFiles(ProductFileVO pvo);

	int insertMainFile(ProductFileVO pvo);

	int getCount(int i);

	List<ProductFileVO> selectList(long pno);

	List<ProductFileVO> selectOneFileList(long pno);

	int deletePrevFile(String uuid);

	int deleteFileList(long pno);

	int cancelDelFie();

	void realDelMainFile();

}