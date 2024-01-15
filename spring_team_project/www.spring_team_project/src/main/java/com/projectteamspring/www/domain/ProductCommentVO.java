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
public class ProductCommentVO {
	private long pcno;
	private long pno;
	private String email;
	private String content;
	private int score;
	private String regAt;
	private List<ProductCommentFileVO> cmtFileList;
	private int recommend;
}
