package com.bbs.demo.controller;

import java.util.Map;

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
	public String board(@RequestParam Map<String, String> params, Model model) {
	    Admin pagecon = new Admin()
	        .setOrderby(params.getOrDefault("orderby", "asc"))
	        .setSortby(params.getOrDefault("sortby", "id"))
	        .setPage(Integer.parseInt(params.getOrDefault("page", "0")))
	        .setLimit(Integer.parseInt(params.getOrDefault("limit", "20")));
	    
		model.addAttribute("boards", adminM.findAllBoardByPage(pagecon));
		return "admin_boards";
	}
	
	@GetMapping("/boards/partial")
	public String boardTablePartial(@RequestParam Map<String, String> params, Model model) {
	    Admin pagecon = new Admin()
	        .setOrderby(params.getOrDefault("orderby", "asc"))
	        .setSortby(params.getOrDefault("sortby", "id"))
	        .setPage(Integer.parseInt(params.getOrDefault("page", "0")))
	        .setLimit(Integer.parseInt(params.getOrDefault("limit", "20")));

	    model.addAttribute("boards", adminM.findAllBoardByPage(pagecon));
	    return "adminboardTable :: tableBodyFragment";
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
