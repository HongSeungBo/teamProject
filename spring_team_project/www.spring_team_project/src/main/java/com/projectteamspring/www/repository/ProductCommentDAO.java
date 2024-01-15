package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectteamspring.www.domain.ProductCommentPagingVO;
import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ProductPagingVO;

public interface ProductCommentDAO {

	int registerText(ProductCommentVO pcvo);

	long getMaxNum();

	long getPnoNum();
	
	int getTotalCount(long pno);

	List<ProductCommentVO> getList(@Param("pno")long pno, @Param("ppvo")ProductCommentPagingVO ppvo);

	int deleteCmt(long pcno);

	int deleteCmtAll(long pno);

	int getSearchTotalCount(@Param("pno")long pno, @Param("ppvo")ProductCommentPagingVO ppvo);

	List<ProductCommentVO> getSearchList(@Param("pno")long pno, @Param("ppvo")ProductCommentPagingVO ppvo);

	void updateRecommendCnt();


}
