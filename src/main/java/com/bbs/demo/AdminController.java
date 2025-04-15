package com.bbs.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/boards")
	public String boards() {
		return "admin_borads";
	}
	
	@GetMapping("/users")
	public String users() {
		return "admin_users";
	}
	
	@GetMapping("/comments")
	public String comments() {
		return "admin_replies";
	}
	
	//삭제 후 돌아오는 요청
}
