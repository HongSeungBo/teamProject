package com.projectteamspring.www.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectteamspring.www.domain.MyCartVO;
import com.projectteamspring.www.domain.MyWishVO;
import com.projectteamspring.www.domain.ProductFileVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ShopDTO;
import com.projectteamspring.www.domain.ShopPagingVO;
import com.projectteamspring.www.domain.ShopVO;
import com.projectteamspring.www.domain.StorageCartVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;
import com.projectteamspring.www.repository.MyCartDAO;
import com.projectteamspring.www.repository.MyWishDAO;
import com.projectteamspring.www.repository.ProductCommentDAO;
import com.projectteamspring.www.repository.ProductCommentFileDAO;
import com.projectteamspring.www.repository.ProductFileDAO;
import com.projectteamspring.www.repository.ShopDAO;
import com.projectteamspring.www.repository.StorageCartDAO;
import com.projectteamspring.www.repository.TmtFileSaveDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ShopDAO sdao;
	
	@Inject
	private TmtFileSaveDAO tdao;
	
	@Inject
	private ProductFileDAO pfdao;
	
	@Inject
	private ProductCommentDAO pcdao;
	
	@Inject
	private ProductCommentFileDAO pcfdao;
	
	@Inject
	private MyWishDAO mwdao;
	
	@Inject
	private StorageCartDAO scdao;
	
	@Inject
	private MyCartDAO mcdao;
	
	
	@Override
	public int tmtporary(TmtFileSaveVO tvo) {
		return tdao.tmtporary(tvo);
	}

	@Override
	public String getImage() {
		return tdao.getMaxImage();
	}

	@Override
	public String getUuid() {
		return tdao.getUuid();
	}

	@Override
	public String getFileName() {
		return tdao.getFileName();
	}

	@Override
	public int tmtSubporary(List<TmtFileSaveVO> flist) {
		int isUp=1;
		int count=0;
		for(TmtFileSaveVO tvo : flist) {
			isUp*= tdao.tmtSubporary(tvo);
			count++;
		}
		return count;
	}

	@Override
	public List<String> getSubImage(int num) {
		return tdao.getSubImage(num);
	}

	@Override
	public List<String> getsubUuid(int num) {
		return tdao.getSubUuid(num);
	}

	@Override
	public List<String> getsubFileName(int num) {
		return tdao.getSubFileName(num);
	}

	@Transactional
	@Override
	public int insert(ShopDTO shopDTO) {
		List<String> tagNameList = shopDTO.getSvo().getTagNameList();
		String tagName = String.join(", ", tagNameList);
		int isOk = sdao.insert(shopDTO.getSvo(), tagName);
		if(isOk>0 && shopDTO.getPFiles().size()>0) {
			long pno = sdao.getSelectPno();
			ProductFileVO pvo = shopDTO.getPFiles().get(0);
			pvo.setPno(pno);
			isOk = pfdao.insertMainFile(pvo);
			if(shopDTO.getPFiles().get(1).getFileSize()>0) {
				for(int i=1; i<shopDTO.getPFiles().size();i++) {
					ProductFileVO subPvo = shopDTO.getPFiles().get(i);
					subPvo.setPno(pno);
					isOk*=pfdao.insertFiles(subPvo);
				}
			}
		}
		
		return isOk;
	}

	@Override
	public int insertNoFile(ShopVO svo) {
		List<String> tagNameList = svo.getTagNameList();
		String tagName = String.join(", ", tagNameList);
		return sdao.insert(svo, tagName);
	}

	@Transactional
	@Override
	public List<ShopVO> getList(ShopPagingVO spvo) {
		sdao.updateSubFileCnt();
		sdao.updateAvgScore();
		sdao.updateCmtQty();
		sdao.updateWishCnt();
		List<ShopVO> shopList = sdao.getList(spvo);
		for (int i = 0; i < shopList.size(); i++) {
		    ShopVO shopVO = shopList.get(i);
		    if (shopList.get(i).getTagName() != null) {
		        String tagNameString = String.join(",", shopList.get(i).getTagName());
		        List<String> splittedTags = Arrays.asList(tagNameString.split(","));
		        shopVO.setTagNameList(splittedTags);
		        shopList.set(i, shopVO);
		    }
		}
		for(int i=0;i<shopList.size();i++) {
			List<ProductFileVO> pvo = pfdao.selectList(shopList.get(i).getPno());
			shopList.get(i).setList(pvo);
		}
		return shopList;
	}

	@Override
	public int getTotalCount(ShopPagingVO spvo) {
		return sdao.getTotalCount(spvo);
	}

	@Transactional
	@Override
	public ShopVO getSelectOne(long pno) {
		ShopVO svo = sdao.getSelectOne(pno);
		if(svo.getTagName()!=null) {
			String tagNameString = String.join(",", svo.getTagName());
	        List<String> splittedTags = Arrays.asList(tagNameString.split(","));
	        svo.setTagNameList(splittedTags);
		}
		svo.setList(pfdao.selectOneFileList(pno));
		return svo;
	}

	@Override
	public List<ShopVO> getProductList(ProductPagingVO ppvo) {
		sdao.updateSubFileCnt();
		List<ShopVO> shopList = sdao.getProductList(ppvo);
		for (int i = 0; i < shopList.size(); i++) {
		    ShopVO shopVO = shopList.get(i);
		    if (shopList.get(i).getTagName() != null) {
		        String tagNameString = String.join(",", shopList.get(i).getTagName());
		        List<String> splittedTags = Arrays.asList(tagNameString.split(","));
		        shopVO.setTagNameList(splittedTags);
		        shopList.set(i, shopVO);
		    }
		}
		for(int i=0;i<shopList.size();i++) {
			List<ProductFileVO> pvo = pfdao.selectList(shopList.get(i).getPno());
			shopList.get(i).setList(pvo);
		}
		return shopList;
	}

	@Override
	public int getProductTotalCount(ProductPagingVO ppvo) {
		return sdao.getProductTotalCount(ppvo);
	}

	@Override
	public int deletePrevFile(String newuuid) {
		return pfdao.deletePrevFile(newuuid);
	}
	@Transactional
	@Override
	public int incModify(ShopDTO shopDTO) {
		List<String> tagNameList = shopDTO.getSvo().getTagNameList();
		String tagName = String.join(", ", tagNameList);
		int isOk = sdao.Modify(shopDTO.getSvo(), tagName);
		if(isOk > 0 && shopDTO.getPFiles().size()>0) {
			long pno = shopDTO.getSvo().getPno();
			ProductFileVO pvo = shopDTO.getPFiles().get(0);
			pvo.setPno(pno);
			isOk *= pfdao.insertMainFile(pvo);
			pfdao.realDelMainFile();
			for(int i=1;i<shopDTO.getPFiles().size();i++) {
				ProductFileVO subPvo = shopDTO.getPFiles().get(i);
				subPvo.setPno(pno);
				isOk *= pfdao.insertFiles(subPvo);
			}
		}
		return isOk;
	}

	@Transactional
	@Override
	public int excModify(ShopDTO shopDTO) {
		List<String> tagNameList = shopDTO.getSvo().getTagNameList();
		String tagName = String.join(", ", tagNameList);
		int isOk = sdao.Modify(shopDTO.getSvo(), tagName);
		if(isOk > 0 && shopDTO.getPFiles().size()>0) {
			long pno = shopDTO.getSvo().getPno();
			for(int i=0; i<shopDTO.getPFiles().size();i++) {
				ProductFileVO subPvo = shopDTO.getPFiles().get(i);
				subPvo.setPno(pno);
				isOk *= pfdao.insertFiles(subPvo);
			}
			pfdao.realDelMainFile();
		}
		return isOk;
	}
	@Override
	public int noFileModify(ShopVO svo) {
		List<String> tagNameList = svo.getTagNameList();
		String tagName = String.join(", ", tagNameList);
		pfdao.realDelMainFile();
		return sdao.Modify(svo, tagName);
	}
	
	@Transactional
	@Override
	public int deleteProduct(long pno) {
		int isOk = sdao.deleteProduct(pno);
		if(isOk>0) {
			pcdao.deleteCmtAll(pno);
			pcfdao.deletCmtFileAll(pno);
		}
		if(isOk>0) {
			isOk *= pfdao.deleteFileList(pno);
		}
		return isOk;
	}
	
	public int searchMyWish(MyWishVO mwvo) {
		int isOk = mwdao.selectEmail(mwvo);
		return isOk;
	}

	@Override
	public int addMyWish(MyWishVO mwvo) {
		return mwdao.insertInfo(mwvo);
	}

	@Override
	public int removeMyWish(MyWishVO mwvo) {
		return mwdao.removeInfo(mwvo);
	}
	
	@Transactional
	@Override
	public List<MyWishVO> selectMyWishList(String email) {
		mwdao.deleteOneWeek(email);
		List<MyWishVO> list = mwdao.selectMyWishList(email);
		return list;
	}

	@Override
	public int editStorageCart(StorageCartVO scart) {
		return scdao.insertInfo(scart);
	}

	@Transactional
	@Override
	public List<StorageCartVO> selectMyCartList(String email) {
		scdao.deleteOneWeek(email);
		List<StorageCartVO> list = scdao.selectMyCartList(email);
		return list;
	}

	@Override
	public int editBuyCart(MyCartVO mcvo) {
		return mcdao.editBuyCart(mcvo);
	}

	@Override
	public List<MyCartVO> selectMCVO(String email) {
		mcdao.deleteOneWeek(email);
		return mcdao.selectMCVO(email);
	}

	@Override
	public int deleteCart(long ctno) {
		return mcdao.delete(ctno);
	}

	@Override
	public int deleteMyWish(long pno, String email) {
		return mwdao.delete(pno, email);
	}

	@Override
	public int deleteMyCartList(StorageCartVO scvo) {
		return scdao.deleteMyCartList(scvo);
	}

	@Override
	public List<ShopVO> getTopWishList() {
		List<ShopVO> shopList = sdao.getTopWishList();
		for(int i=0;i<shopList.size();i++) {
			List<ProductFileVO> pvo = pfdao.selectList(shopList.get(i).getPno());
			shopList.get(i).setList(pvo);
		}
		return shopList;
	}

	@Override
	public int cancelDelFile() {
		return pfdao.cancelDelFie();
	}

	
	
//	@Transactional
//	@Override
//	public List<ShopVO> getList(ShopPagingVO spvo) {
//		long maxNo = sdao.getMaxPno();
//		List<ShopVO> shopList = sdao.getList(spvo);
//		log.info("serivceImpl maxNo >> " + maxNo);
//		for(int i=0; i<=maxNo; i++) {
//			int count = pfdao.getCount(i);
//			if(count>0) {
//				log.info("count >>>>>> " + count);
//				log.info("i>>>>>>> " + i);
//				ShopVO svo = new ShopVO();
//				
//				svo.setList(pfdao.selectList(i));
//				shopList.add(svo);
//				log.info("svo List >>>>>>> " + svo.getList());
//			}
//		}
//		return shopList;
//	}

}
