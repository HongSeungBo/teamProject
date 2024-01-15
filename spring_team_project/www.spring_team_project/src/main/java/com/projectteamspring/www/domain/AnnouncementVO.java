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
public class AnnouncementVO {

	private long ano;
	private String writer;
	private String title;
	private String content;
	private String regAt;
	private int readCount;
	private String modAt;
	
	
}
