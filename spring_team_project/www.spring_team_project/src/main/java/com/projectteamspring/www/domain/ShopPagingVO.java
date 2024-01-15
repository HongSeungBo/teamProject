package com.projectteamspring.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShopPagingVO {
	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	private String tagType;
	
	public ShopPagingVO() {
		this(1,6, null);
	}
	
	public ShopPagingVO(int pageNo, int qty, String tagType) {
		this.pageNo = pageNo;
		this.qty = qty;
		this.tagType = tagType;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	public String[] getTypeToArray() {
		return this.type == null? new String[] {}: this.type.split("");
	}
	public String[] getTagTypeToArray() {
		return this.tagType == null? new String[] {}: this.tagType.split("");
	}
}
