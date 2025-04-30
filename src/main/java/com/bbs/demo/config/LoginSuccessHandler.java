package com.bbs.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.bbs.demo.domain.UserGrade;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.LoginMemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private LoginMemberMapper loginMemberMapper;
	
	public LoginSuccessHandler() {
        // 기본 URL 지정
        setDefaultTargetUrl("/board/list");
    }
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String email = authentication.getName();
        Users user = loginMemberMapper.findbyEmail(email);
        user.setUserGradeString((UserGrade.fromGrade(user.getUserGrade())).toString());

        // 세션에 저장
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", user);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
