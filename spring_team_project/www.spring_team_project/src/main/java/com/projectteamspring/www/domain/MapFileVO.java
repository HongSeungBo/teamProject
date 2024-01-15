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
public class MapFileVO {
	
	private String uuid;
	private long mno;
	private String saveDir;
	private String fileName;
	private String regAt;

}
