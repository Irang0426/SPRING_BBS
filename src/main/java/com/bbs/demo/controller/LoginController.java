package com.bbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import com.bbs.demo.domain.Member;
import com.bbs.demo.mapper.MemberMapper;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private MemberMapper memberMapper;

    @GetMapping("/")
    public String home() {
        return "main";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam String loginid,
                                @RequestParam String password,
                                HttpSession session) {
        Member newMember = new Member();
        newMember.setLoginid(loginid);
        newMember.setPassword(password);
        memberMapper.insert(newMember);

        session.setAttribute("loginMember", newMember);
        return "redirect:/main";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
