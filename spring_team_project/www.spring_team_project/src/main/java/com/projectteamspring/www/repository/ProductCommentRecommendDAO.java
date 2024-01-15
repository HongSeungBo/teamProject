package com.projectteamspring.www.repository;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.ProductCommentRecommendVO;

public interface ProductCommentRecommendDAO {

	int selectId(ProductCommentRecommendVO prvo);

	int insertId(ProductCommentRecommendVO prvo);

	int removeId(ProductCommentRecommendVO prvo);

}
