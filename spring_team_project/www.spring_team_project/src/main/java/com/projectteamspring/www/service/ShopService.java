package com.projectteamspring.www.service;

import java.util.List;

import com.projectteamspring.www.domain.MyCartVO;
import com.projectteamspring.www.domain.MyWishVO;
import com.projectteamspring.www.domain.ProductFileVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ShopDTO;
import com.projectteamspring.www.domain.ShopPagingVO;
import com.projectteamspring.www.domain.ShopVO;
import com.projectteamspring.www.domain.StorageCartVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;

public interface ShopService {

	int tmtporary(TmtFileSaveVO tvo);

	String getImage();

	String getUuid();

	String getFileName();

	int tmtSubporary(List<TmtFileSaveVO> flist);

	List<String> getSubImage(int num);

	List<String> getsubUuid(int num);

	List<String> getsubFileName(int num);

	int insert(ShopDTO shopDTO);

	int insertNoFile(ShopVO svo);

	List<ShopVO> getList(ShopPagingVO spvo);

	int getTotalCount(ShopPagingVO spvo);

	ShopVO getSelectOne(long pno);

	List<ShopVO> getProductList(ProductPagingVO ppvo);

	int getProductTotalCount(ProductPagingVO ppvo);

	int deletePrevFile(String newuuid);

	int incModify(ShopDTO shopDTO);
	
	int excModify(ShopDTO shopDTO);

	int noFileModify(ShopVO svo);

	int deleteProduct(long pno);

	int searchMyWish(MyWishVO mwvo);

	int addMyWish(MyWishVO mwvo);
	
	int removeMyWish(MyWishVO mwvo);

	List<MyWishVO> selectMyWishList(String email);

	int editStorageCart(StorageCartVO scart);

	List<StorageCartVO> selectMyCartList(String email);

	int editBuyCart(MyCartVO mcvo);

	List<MyCartVO> selectMCVO(String email);

	int deleteCart(long ctno);

	int deleteMyWish(long pno, String email);

	int deleteMyCartList(StorageCartVO scvo);

	List<ShopVO> getTopWishList();

	int cancelDelFile();

}
