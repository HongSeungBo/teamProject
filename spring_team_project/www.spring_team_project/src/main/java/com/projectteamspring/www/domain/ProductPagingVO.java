package com.projectteamspring.www.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class ProductPagingVO {
	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	private String tagType;
	
	public ProductPagingVO() {
		this(1,10, null);
	}
	
	public ProductPagingVO(int pageNo, int qty, String tagType) {
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

