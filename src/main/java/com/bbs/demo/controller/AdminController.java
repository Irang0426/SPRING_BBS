package com.bbs.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.demo.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminS;
	
	@GetMapping("")
	public String adminMain(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("users", adminS.getUsers(params));
		return "admin";
	}
	
	@GetMapping("/users")
	public String users(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("users", adminS.getUsers(params));
		return "admin_tables :: usertable";
	}
	
	@GetMapping("/boards")
	public String boards(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("boards", adminS.getBoards(params));
		return "admin_tables :: boardtable";
	}
	
	@GetMapping("/notes")
	public String notes(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("notes", adminS.getNotes(params));
		return "admin_tables :: notetable";
	}
	
	@GetMapping("/comments")
	public String comments(@RequestParam Map<String, String> params, Model model) {
	    model.addAttribute("comments", adminS.getComments(params));
		return "admin_tables :: commenttable";
	}

	@GetMapping("/users/partial")
	public String userTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("users", adminS.getUsers(params));
		return "admin_tables :: usertbody";
	}
	
	@GetMapping("/boards/partial")
	public String boardTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("boards", adminS.getBoards(params));
		return "admin_tables :: boardtbody";
	}
	
	@GetMapping("/notes/partial")
	public String noteTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("notes", adminS.getNotes(params));
		return "admin_tables :: notetbody";
	}
	
	@GetMapping("/comments/partial")
	public String commentTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("comments", adminS.getComments(params));
		return "admin_tables :: commenttbody";
	}
	
	//삭제 후 돌아오는 요청
}
