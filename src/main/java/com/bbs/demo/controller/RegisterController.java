package com.bbs.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.LoginMemberMapper;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
    private LoginMemberMapper loginMemberMapper;

    @GetMapping
    public String showRegisterForm() {
        return "register"; 
    }

    @PostMapping
    public String processRegister(@RequestParam(name="nickName") String nickName,
                                  @RequestParam(name="email") String email,
                                  @RequestParam(name="password") String password,
                                  Model model) {
    	
		/*
		 * 회원가입시 이메일 중복 체크를 넣어 봤는데, 일단 주석 처리 해놓을께요 필요하면 쓰면 좋을거 같습니다!
		<---------------             아래가 코드 입니다    ----------------------------------->
		 * Users existUser = loginMemberMapper.findloginid(email); if (exisUser != null)
		 * { model.addAttribute("주의!", "이미 사용중인 이메일 입니다."); 
		 * return "register"; }
		 */

        Users newUser = new Users();
        newUser.setNickName(nickName);
        newUser.setEmail(email);
        newUser.setPassword(password); // 나중에 암호화 고려
        newUser.setRegDate(LocalDateTime.now());
        newUser.setLastAccessDate(LocalDateTime.now());
        newUser.setUserGrade(1);

        loginMemberMapper.insert(newUser);
        
        System.out.println("회원가입성공");
        return "redirect:/login";
    }
}


