package com.projectteamspring.www.handler;

import com.projectteamspring.www.domain.MemberPagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberPagingHandler {

	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev, next;
	
	private int totalCount;
	private MemberPagingVO mgvo;
	
	public MemberPagingHandler(MemberPagingVO mgvo, int totalCount) {
		this.mgvo=mgvo;
		this.totalCount = totalCount;
		
		this.endPage = 
				(int)Math.ceil(mgvo.getPageNo() / (double)mgvo.getQty())*mgvo.getQty();
		
		this.startPage = endPage -4 ;
		
		this.realEndPage =
				(int)Math.ceil(totalCount / (double)mgvo.getQty());
		
		if (realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
