package com.projectteamspring.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardPagingVO {

	private int pageNo;
	private int qty;
	private String type;
	private String animalType;
	
	private String keyword;
	
	public BoardPagingVO() {
		this(1,10);
	}
	
	public BoardPagingVO(int pageNo, int qty) {
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
