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
public class ProductFileVO {
	private String uuid;
	private long pno;
	private String saveDir;
	private long fileSize;
	private String fileName;
	private boolean main;
	private String regAt;
	private String DelType;
	private String tmtDel;
}