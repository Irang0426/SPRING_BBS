package com.bbs.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bbs.demo.domain.UserGrade;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.LoginMemberMapper;


@Configuration
@EnableWebSecurity
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private LoginMemberMapper loginMemberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users member = loginMemberMapper.findbyEmail(username);
		
		if(member == null) {
			new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
		}
		

		System.out.println("\n\n\n\n\n\n" + member.getEmail());
		System.out.println(member.getPassword() + "\n\n\n\n\n\n\n");

		return org.springframework.security.core.userdetails.User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles((UserGrade.fromGrade(member.getUserGrade())).toString())
				.build();
	}
}
