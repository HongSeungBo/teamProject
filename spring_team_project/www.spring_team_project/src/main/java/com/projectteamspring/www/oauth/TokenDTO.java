package com.projectteamspring.www.oauth;

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
public class TokenDTO {

	private String access_token;
	private String refresh_token;
	private String scope;
	private String token_type;
	private long expires_in;
	private String id_token;
	
}
