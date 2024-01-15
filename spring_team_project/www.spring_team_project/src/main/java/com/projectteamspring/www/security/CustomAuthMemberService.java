package com.projectteamspring.www.security;

import javax.inject.Inject;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.projectteamspring.www.repository.MemberDAO;

public class CustomAuthMemberService implements UserDetailsService{

	@Inject
	private MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		MemberVO mvo = mdao.selectEmail(username);
		if (mvo == null) {
			throw new UsernameNotFoundException(username);
		}
		if ("Y".equals(mvo.getIsDel())) {
            throw new DisabledException("This account has been disabled");
        }
		
		mvo.setAuthList(mdao.selectAuths(username));
		return new AuthMember(mvo);
	}

}
