package com.bbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.mapper.AdminMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminMapper adminM;
	
	@GetMapping("/boards")
	public String boards(@RequestParam(name="sortby", defaultValue="id")String sortby,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="limit", defaultValue="20") int limit,
			@RequestParam(name="orderby", defaultValue="asc") String orderby,
			Model model) {
		Admin pagecon = new Admin().setOrderby(orderby).setPage(page).setLimit(limit).setSortby(sortby);
		model.addAttribute("boards", adminM.findAllBoardByPage(pagecon));
		return "admin_boards";
	}
	
	@GetMapping("/notes")
	public String notes(Model model) {
		//model.addAttribute("notes", adminM.findAllNoteByPage());
		return "admin_notes";
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
