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
public class MemberFileVO {
	
	private String uuid;
	private String email;
	private String saveDir;
	private long fileSize;
	private String fileName;
	private String regAt;
	private String DelType;
	

}
