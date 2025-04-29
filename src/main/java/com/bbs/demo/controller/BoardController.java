package com.bbs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Page;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.BoardMapper;
import com.bbs.demo.mapper.NoteMapper;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardMapper boardmapper;
	
	@Autowired
	private NoteMapper notemapper;
	
	@GetMapping("/list")
	public String boardList(Model model, HttpSession session) {
		Page page = new Page();
		Users loginMember = (Users)session.getAttribute("loginMember");
		
//		if(loginMember == null) {
//			return "/login";
//		}
		
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("boardList", boardmapper.findAllBoard());
		model.addAttribute("pageInfo", page);
		
		return "board_list";
	}
	
	@PostMapping("/notelist")
	@ResponseBody
	public List<Notes> noteListByBoardId(@RequestParam(name = "boardId") String id , Model model) {
		System.out.println("GET FETCH!");
		List<Notes> notelist = notemapper.findByBoardId(Integer.parseInt(id));
		return notelist;
	}
}
