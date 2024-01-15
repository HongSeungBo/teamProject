package com.projectteamspring.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberPagingVO {

	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	
	public MemberPagingVO() {
		
		this(1,5);
	}
	
	
	public MemberPagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
}
