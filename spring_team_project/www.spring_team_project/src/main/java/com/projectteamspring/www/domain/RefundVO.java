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
public class RefundVO {
	
	private String uid;
	private String reason;
	private String refundses;
	private String access_token;
	private String regAt;
	private PayMentVO pmvo;

}
