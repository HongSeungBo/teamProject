package com.projectteamspring.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentFileVO {
	private String uuid;
	private long pcno;
	private String saveDir;
	private String fileName;
	private long pno;
}
