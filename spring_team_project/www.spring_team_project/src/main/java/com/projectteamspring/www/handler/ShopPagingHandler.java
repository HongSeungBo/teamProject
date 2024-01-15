package com.projectteamspring.www.handler;

import java.util.List;

import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ShopPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShopPagingHandler {
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private ShopPagingVO spvo;
	private List<ProductCommentVO> pCmtList;
	
	public ShopPagingHandler(ShopPagingVO spvo, int totalCount) {
		this.spvo = spvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(spvo.getPageNo() / (double)spvo.getQty()) * spvo.getQty();
		this.startPage = endPage - 5;
		
		this.realEndPage = (int)Math.ceil(totalCount / (double)spvo.getQty());
		if(realEndPage<endPage) {
			this.endPage = realEndPage;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage<realEndPage;
	}
	
	public ShopPagingHandler(ShopPagingVO spvo, int totalCount, List<ProductCommentVO> pCmtList) {
		this(spvo, totalCount);
		this.pCmtList = pCmtList;
	}
}
