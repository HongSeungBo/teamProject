package com.projectteamspring.www.handler;

import java.util.List;

import com.projectteamspring.www.domain.ProductCommentPagingVO;
import com.projectteamspring.www.domain.ProductCommentVO;
import com.projectteamspring.www.domain.ProductPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCommentPagingHandler {
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private ProductCommentPagingVO pcpvo;
	private List<ProductCommentVO> cmtList;
	
	public ProductCommentPagingHandler(ProductCommentPagingVO pcpvo, int totalCount) {
		this.pcpvo = pcpvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pcpvo.getPageNo() / (double)pcpvo.getQty()) * pcpvo.getQty();
		this.startPage = endPage - 9;
		
		this.realEndPage = (int)Math.ceil(totalCount / (double)pcpvo.getQty());
		if(realEndPage<endPage) {
			this.endPage = realEndPage;
		}
		this.prev=this.startPage>1;
		this.next=this.endPage<realEndPage;
	}
	
	public ProductCommentPagingHandler(ProductCommentPagingVO pcpvo, int totalCount, List<ProductCommentVO> cmtList) {
		this(pcpvo, totalCount);
		this.cmtList = cmtList;
	}
}
