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
public class PayMentVO {
	private String pg;
	private String buyerEmail;
	private String buyerName;
	private String buyerTel;
	private String buyerAddr;	
	private String name;
	private int amount;
	private String payMethod;
	private String impUid;
	private String regAt;
	private String refundAt;
	private int status;
}
