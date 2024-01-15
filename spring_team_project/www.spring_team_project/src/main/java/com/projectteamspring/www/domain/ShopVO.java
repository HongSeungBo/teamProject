package com.projectteamspring.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShopVO {
	private long pno;
	private String productName;
	private int price;
	private List<String> tagNameList;
	private String tagName;
	private String productContent;
	private String classification;
	private String regAt;
	private String delType;
	private List<ProductFileVO> list;
	private int subFileCnt;
	private float avgScore;
	private int cmtQty;
	private int wishCnt;
}
