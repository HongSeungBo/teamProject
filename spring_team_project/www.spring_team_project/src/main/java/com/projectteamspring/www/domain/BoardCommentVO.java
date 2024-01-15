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
public class BoardCommentVO {

	private long cno;
	private long bno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
}
