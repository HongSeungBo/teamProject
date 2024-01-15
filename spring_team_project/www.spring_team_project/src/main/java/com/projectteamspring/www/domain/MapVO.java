package com.projectteamspring.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MapVO {
	
	private long mno;
	private double lat;
	private double lon;
	private String shopName;
	private String sec;
	private String animalType;
	private String stTime;
	private String edTime;
	private String num;
	private String detail;
	private String url;

}
