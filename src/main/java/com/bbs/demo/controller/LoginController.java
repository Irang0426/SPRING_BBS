package com.bbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.LoginMemberMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController{
	
	@Autowired
	public LoginMemberMapper loginMemberMapper;
	
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
		
		if(member == null || !member.getPassword().equals(password)) {
			model.addAttribute("error", "아이디 또는 비밀번호를 잘못 입력하였습니다!");
			return "login";
		}
		session.setAttribute("loginMember", member);
		System.out.print("!!!!!!!! 로그인 성공했습니다!!!!!!!!!!");
		
		return "redirect:/sample/list";
	}
}

