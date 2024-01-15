package com.projectteamspring.www.handler;

import java.util.List;

import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ProductPagingVO;
import com.projectteamspring.www.domain.ShopPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPagingHandler {
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private ProductPagingVO ppvo;
	private List<ProductCommentVO> cmtList;
	
	public ProductPagingHandler(ProductPagingVO ppvo, int totalCount) {
		this.ppvo = ppvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(ppvo.getPageNo() / (double)ppvo.getQty()) * ppvo.getQty();
		this.startPage = endPage - 9;
		
		this.realEndPage = (int)Math.ceil(totalCount / (double)ppvo.getQty());
		if(realEndPage<endPage) {
			this.endPage = realEndPage;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage<realEndPage;
	}
	
	public ProductPagingHandler(ProductPagingVO ppvo, int totalCount, List<ProductCommentVO> cmtList) {
		this(ppvo, totalCount);
		this.cmtList = cmtList;
	}
}
