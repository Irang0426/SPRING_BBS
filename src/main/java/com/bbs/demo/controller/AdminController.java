package com.bbs.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		String url = params.getOrDefault("url", "users");
		System.out.println(url);
		if(url.equals("users")) {
			model.addAttribute("users", adminS.getUsers(params));
		} else if (url.equals("boards")) {
			model.addAttribute("boards", adminS.getBoards(params));
		} else if (url.equals("notes")) {
			model.addAttribute("notes", adminS.getNotes(params));
		} else if (url.equals("comments")) {
			model.addAttribute("comments", adminS.getComments(params));
		} else if (url.equals("files")) {
			model.addAttribute("files", adminS.getFiles(params));
		}
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		return "admin";
	}
	
	@GetMapping("/users")
	public String users(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("users", adminS.getUsers(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		System.out.println("----------------------페이지 총 개수: "+adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: usertable";
	}
	
	@GetMapping("/boards")
	public String boards(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("boards", adminS.getBoards(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		System.out.println("----------------------페이지 총 개수: "+adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: boardtable";
	}
	
	@GetMapping("/notes")
	public String notes(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("notes", adminS.getNotes(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		System.out.println("----------------------페이지 총 개수: "+adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: notetable";
	}
	
	@GetMapping("/comments")
	public String comments(@RequestParam Map<String, String> params, Model model) {
	    model.addAttribute("comments", adminS.getComments(params));
		System.out.println("----------------------들어가는 파라미터: "+params);
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		System.out.println("----------------------페이지 총 개수: "+adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: commenttable";
	}
	
	@GetMapping("/files")
	public String files(@RequestParam Map<String, String> params, Model model) {
	    model.addAttribute("files", adminS.getFiles(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: filetable";
	}

	@GetMapping("/users/partial")
	public String usersTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("users", adminS.getUsers(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
	    return "admin_tables :: usertbody";
	}
	
	@GetMapping("/boards/partial")
	public String boardsTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("boards", adminS.getBoards(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: boardtbody";
	}
	
	@GetMapping("/notes/partial")
	public String notesTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("notes", adminS.getNotes(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: notetbody";
	}
	
	@GetMapping("/comments/partial")
	public String commentsTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("comments", adminS.getComments(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: commenttbody";
	}
	
	@GetMapping("/files/partial")
	public String filesTablePartial(@RequestParam Map<String, String> params, Model model) {
		model.addAttribute("files", adminS.getFiles(params));
		model.addAttribute("totalPageCount", adminS.createPageCondition(params).getTotalPageCount());
		return "admin_tables :: filetbody";
	}
	
	@PostMapping("/boards/create")
	public String createBoard(@RequestParam Map<String, String> params) {
	    return "redirect:/admin?" + adminS.createBoard(params);
	}
	
	@PostMapping("/users/updateGrade")
	public String updateUserGrade(@RequestParam("params") String rawParams, @RequestParam("id") int id, @RequestParam("newGrade") int newGrade) {
		adminS.updateUserGrade(id, newGrade);
		return "redirect:/admin?"+ rawParams;
	}
	
	//삭제 후 돌아오는 요청
	@PostMapping("/users/delete")
	public String deleteUsers(@RequestParam("params") String rawParams, @RequestParam("id") int id) {
		adminS.deleteUsers(id);
		return "redirect:/admin?"+ rawParams;
	}

	@PostMapping("/boards/delete")
	public String deleteBoards(@RequestParam("params") String rawParams, @RequestParam("id") int id) {
		adminS.deleteBoards(id);
		return "redirect:/admin?"+ rawParams;
	}

	@PostMapping("/notes/delete")
	public String deleteNotes(@RequestParam("params") String rawParams, @RequestParam("id") int id) {
		adminS.deleteNotes(id);
		return "redirect:/admin?"+ rawParams;
	}

	@PostMapping("/comments/delete")
	public String deleteComments(@RequestParam("params") String rawParams, @RequestParam("id") int id) {
		adminS.deleteComments(id);
		return "redirect:/admin?"+ rawParams;
	}

	@PostMapping("/files/delete")
	public String deleteFiles(@RequestParam("params") String rawParams, @RequestParam("id") int id) {
		adminS.deleteFiles(id);
		return "redirect:/admin?"+ rawParams;
	}
}
