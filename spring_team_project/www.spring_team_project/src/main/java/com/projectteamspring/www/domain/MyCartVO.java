package com.projectteamspring.www.domain;

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
public class MyCartVO {
	
	private long ctno;
	private String email;
	private long pno;
	private String productName;
	private int price;
	private int productCount;
	private int totalprice;
	
}
