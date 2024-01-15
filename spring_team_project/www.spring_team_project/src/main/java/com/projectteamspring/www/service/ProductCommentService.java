package com.projectteamspring.www.service;

import java.util.List;

import com.projectteamspring.www.domain.ProductCommentFileVO;
import com.projectteamspring.www.domain.ProductCommentPagingVO;
import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ProductCommentRecommendVO;
import com.projectteamspring.www.handler.ProductCommentPagingHandler;
import com.projectteamspring.www.handler.ProductPagingHandler;

public interface ProductCommentService {

	int registerText(ProductCommentVO pcvo);

	int registerFile(List<ProductCommentFileVO> flist);

	ProductCommentPagingHandler getList(long pno, ProductCommentPagingVO pcpvo);

//	List<ProductCommentFileVO> getFileInfo(long pcno);

	int deleteCmt(long pcno);

	ProductCommentPagingHandler getSearchList(long pno, ProductCommentPagingVO pcpvo);

	int selectRecommend(ProductCommentRecommendVO prvo);

	int addRecommend(ProductCommentRecommendVO prvo);

	int removeRecommend(ProductCommentRecommendVO prvo);

}
