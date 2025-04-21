// 보트 리스트 출력 전용 테스트 파일입니다.

package com.bbs.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.demo.domain.Note;
import com.bbs.demo.mapper.BoardMapper;
import com.bbs.demo.mapper.NoteMapper;

@Controller
@RequestMapping("/listtest")
public class BoardlistTestController {

	@Autowired
	private BoardMapper boardmapper;
	
	@Autowired
	private NoteMapper notemapper;
	
	@GetMapping("/boardlisttest")
	public String boardList(Model model) {
		model.addAttribute("boardList", boardmapper.findAllBoard());
		return "board_list";
	}
	
	@PostMapping("/notelisttest")
	@ResponseBody
	public List<Note> noteListByBoardId(@RequestParam(name = "boardId") String id ,Model model) {
		System.out.println("GET FETCH!");
		List<Note> notelist = notemapper.findByBoardId(Integer.parseInt(id));
		return notelist;
	}
	
}
