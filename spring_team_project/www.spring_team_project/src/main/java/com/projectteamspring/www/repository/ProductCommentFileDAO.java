package com.projectteamspring.www.repository;

import java.util.List;

import com.projectteamspring.www.domain.ProductCommentFileVO;

public interface ProductCommentFileDAO {

	int registerFile(ProductCommentFileVO file);

//	List<ProductCommentFileVO> getFileInfo(long pcno);

	int getFileCount(long pcno);

	List<ProductCommentFileVO> setFileList(long pcno);

	int deleteCmtFile(long pcno);

	int selectCount(long pcno);

	int deletCmtFileAll(long pno);

}
