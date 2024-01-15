package com.projectteamspring.www.security;

import java.util.List;

import com.projectteamspring.www.domain.MemberFileVO;

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
public class MemberVO {

	private String email;
	private String pwd;
	private String name;
	private String nickName;
	
	private String regAt;
	private String birth;
	private String lastLogin;

	private String address;
	private String phonNum;

	private String phoNum;

	private String platform;
	
	
	private List<AuthVO> authList;
	private String isDel;
	private MemberFileVO mfvo;
	
	private String region;
	private String subRegion;
	private String detailaddress;
	
	private String birthYear;
	private String birthMonth;
	private String birthDay;
	
	private String phon1;
	private String phon2;
	private String phon3;
}
