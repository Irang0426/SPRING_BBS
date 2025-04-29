package com.bbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.demo.domain.UserGrade;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.LoginMemberMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController{
	
	@Autowired
	private LoginMemberMapper loginMemberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam(name = "loginid") String loginid,
						@RequestParam(name = "password") String password,
						HttpSession session,
						Model model) {
		
		Users member = loginMemberMapper.findbyEmail(loginid);
		
		if(member == null || !passwordEncoder.matches(password, member.getPassword())) {
			model.addAttribute("error", "아이디 또는 비밀번호를 잘못 입력하였습니다!");
			return "login";
		}
		

		Users loginMember = new Users();
		
//		System.out.println("\n\n\n" + member.getUserGrade());
//		System.out.println(member.getId());
//		System.out.println(member.getEmail());
//		System.out.println(member.getNickName());
//		System.out.println(member.getRegDate());
//		System.out.println(member.getLastAccessDate() + "\n\n\n");


		loginMember.setId(member.getId());
		loginMember.setEmail(member.getEmail());
		loginMember.setNickName(member.getNickName());
		loginMember.setUserGrade(member.getUserGrade());
		loginMember.setRegDate(member.getRegDate());
		loginMember.setLastAccessDate(member.getLastAccessDate());
		loginMember.setUserGradeString((UserGrade.fromGrade(loginMember.getUserGrade())).toString());
		
		session.setAttribute("loginMember", loginMember);
		System.out.print("!!!!!!!! 로그인 성공했습니다!!!!!!!!!!");
		
		return "redirect:/board/list";
	}
}