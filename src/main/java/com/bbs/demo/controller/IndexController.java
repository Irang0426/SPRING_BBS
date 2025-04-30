package com.bbs.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.UserGrade;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.IndexMapper;

@Controller
@RequestMapping("/data")
public class IndexController {
	
	@Autowired
	IndexMapper indexMapper;
	
	@PostMapping("/datas")
	@ResponseBody
	public Map<String, Object> getAllData() {
		
		int AllMember = indexMapper.userCount();
		int AllNotes = indexMapper.noteCount();
		int AllFiles = indexMapper.fileCount();
		int AllNoteFiles = indexMapper.fileNoteCount();
		int AllComments = indexMapper.commentCount();
		Users newUser = indexMapper.newUser(AllMember);
		newUser.setUserGradeString(UserGrade.fromGrade(newUser.getUserGrade()).toString());
		List<Notes> newNotes = indexMapper.newNotes(AllNotes);
		

		return Map.of(
				"AllMember", AllMember,
				"AllNotes", AllNotes,
				"AllFiles", AllFiles,
				"AllNoteFiles", AllNoteFiles,
				"AllComments", AllComments,
				"newUser", newUser,
				"newNotes", newNotes
				);
	}
}
