package com.projectteamspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ShopPagingVO;
import com.projectteamspring.www.domain.ShopVO;

public interface ShopDAO {

	int insert(@Param("svo")ShopVO svo, @Param("tagName")String tagName);

	long getSelectPno();

	List<ShopVO> getList(ShopPagingVO spvo);

	long getMaxPno();

	int getTotalCount(ShopPagingVO spvo);

	ShopVO getSelectOne(long pno);

	void updateSubFileCnt();
	
	void updateAvgScore();

	void updateCmtQty();

	void updateWishCnt();
	
	int getProductTotalCount(ProductPagingVO ppvo);

	List<ShopVO> getProductList(ProductPagingVO ppvo);
	
	int Modify(@Param("svo")ShopVO svo, @Param("tagName")String tagName);

	int deleteProduct(long pno);

	List<ShopVO> getTopWishList();

}
