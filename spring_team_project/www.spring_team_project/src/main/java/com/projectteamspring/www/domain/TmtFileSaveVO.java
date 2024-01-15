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
public class TmtFileSaveVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private String regAt;
}
