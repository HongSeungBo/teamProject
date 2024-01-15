package com.projectteamspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectteamspring.www.domain.ProductCommentFileVO;
import com.projectteamspring.www.domain.ProductCommentPagingVO;
import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ProductCommentRecommendVO;
import com.projectteamspring.www.handler.ProductCommentPagingHandler;
import com.projectteamspring.www.handler.ProductPagingHandler;
import com.projectteamspring.www.repository.ProductCommentDAO;
import com.projectteamspring.www.repository.ProductCommentFileDAO;
import com.projectteamspring.www.repository.ProductCommentRecommendDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductCommentServiceImpl implements ProductCommentService {
	
	@Inject
	private ProductCommentDAO pcdao;
	
	@Inject
	private ProductCommentFileDAO pcfdao;
	
	@Inject
	private ProductCommentRecommendDAO pcrdao;

	@Override
	public int registerText(ProductCommentVO pcvo) {
		return pcdao.registerText(pcvo);
	}

	@Transactional
	@Override
	public int registerFile(List<ProductCommentFileVO> flist) {
		long num = pcdao.getMaxNum();
		long pno = pcdao.getPnoNum();
		int isOk = 1;
		if(num>0) {
			for(ProductCommentFileVO file : flist) {
				file.setPno(pno);
				file.setPcno(num);
				isOk *= pcfdao.registerFile(file);
			}
		}
		return isOk;
	}

	@Transactional
	@Override
	public ProductCommentPagingHandler getList(long pno, ProductCommentPagingVO pcpvo) {
		pcdao.updateRecommendCnt();
		int totalCount = pcdao.getTotalCount(pno);
		List<ProductCommentVO> list = pcdao.getList(pno, pcpvo);
		for(int i=0;i<list.size();i++) {
			long pcno = list.get(i).getPcno();
			int count = pcfdao.getFileCount(pcno);
			if(count>0) {
				list.get(i).setCmtFileList(pcfdao.setFileList(pcno));
			}
		}
		ProductCommentPagingHandler pph = new ProductCommentPagingHandler(pcpvo, totalCount, list);
		return pph;
	}

//	@Override
//	public List<ProductCommentFileVO> getFileInfo(long pcno) {
//		List<ProductCommentFileVO> list = pcfdao.getFileInfo(pcno);
//		return list;
//	}

	@Transactional
	@Override
	public int deleteCmt(long pcno) {
		int isOk = pcdao.deleteCmt(pcno);
		int count = pcfdao.selectCount(pcno);
		if(count>0) {
			isOk = pcfdao.deleteCmtFile(pcno);
		}
		return isOk;
	}

	@Override
	public ProductCommentPagingHandler getSearchList(long pno, ProductCommentPagingVO pcpvo) {
		int totalCount = pcdao.getSearchTotalCount(pno, pcpvo);
		log.info("search totalCount >>>>> " + totalCount);
		List<ProductCommentVO> list = pcdao.getSearchList(pno, pcpvo);
		for(int i=0;i<list.size();i++) {
			long pcno = list.get(i).getPcno();
			int count = pcfdao.getFileCount(pcno);
			if(count>0) {
				list.get(i).setCmtFileList(pcfdao.setFileList(pcno));
			}
		}
		ProductCommentPagingHandler pph = new ProductCommentPagingHandler(pcpvo, totalCount, list);
		return pph;
	}

	@Override
	public int selectRecommend(ProductCommentRecommendVO prvo) {
		int isOk = pcrdao.selectId(prvo);
		return isOk;
	}

	@Override
	public int addRecommend(ProductCommentRecommendVO prvo) {
		return pcrdao.insertId(prvo);
	}

	@Override
	public int removeRecommend(ProductCommentRecommendVO prvo) {
		return pcrdao.removeId(prvo);
	}

}
