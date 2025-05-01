package com.bbs.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.bbs.demo.domain.Token;
import com.bbs.demo.mapper.PageMapper;
import com.bbs.demo.service.Tokenizer;

@Controller
@RequestMapping("/pagingtest")
public class PageController {

	@Autowired
	PageMapper pagemapper;
	
	@ResponseBody
	@PostMapping("/page")
	public Map<String, Object> PageList(@RequestParam(name="boardPos") int boardPos,
							@RequestParam(name="pagePos") int pagePos,
							Model model) {
		
		List<Notes> pageList = new ArrayList<>();
		Page page = new Page();
		
		if(boardPos == 0) {
			int pagecount = pagemapper.pageAllCount();
			if(pagecount % page.getPageSize() != 0) {
				pagecount = (int)(pagecount / page.getPageSize()) + 1;
			}
			else {
				pagecount = (int)(pagecount / page.getPageSize());
			}
			
			page.setBoardPos(boardPos);
			page.setPage(pagePos);
			page.setPageLen(pagecount);
			
			pageList = pagemapper.findAllPage(page);
		}
		else {
			int pagecount = pagemapper.pageCount(boardPos);
			if(pagecount % page.getPageSize() != 0) {
				pagecount = (int)(pagecount / page.getPageSize()) + 1;
			}
			else {
				pagecount = (int)(pagecount / page.getPageSize());
			}
			
			page.setBoardPos(boardPos);
			page.setPage(pagePos);
			page.setPageLen(pagecount);
			
			pageList = pagemapper.findInPage(page);
		}
		
		return Map.of(
					"noteList", pageList,
					"pageInfo", page
				);
	}
	
	@ResponseBody
	@PostMapping("/noteSearch")
	public Map<String, Object> NoteSearch(@RequestParam(name="boardPos") int boardPos,
										@RequestParam(name="content") String content,
										Model model) {
		
		Tokenizer tokenizer = new Tokenizer();
		
		List<String> tokens = tokenizer.tokenizer(content);
		// 토큰화된 검색어중 1번만 이용하여 note_token 검색 -> note_id 리스트 리턴
//		select note_id from note_token
//		where token = "여행"
//	    group by note_id;
		
		Map<String, Object> params = new HashMap<>();
		Set<String> uniqueTokens = new HashSet<>(tokens);
		List<Notes> searchResult = new ArrayList<>();
		
		if(boardPos == 0) {
			params.put("tokens", uniqueTokens);
			params.put("count", tokens.size());
			
			searchResult = pagemapper.noteAllList(params);
		}
		else {
			params.put("board", boardPos);
			params.put("tokens", uniqueTokens);
			params.put("count", tokens.size());
			
			searchResult = pagemapper.noteList(params);
		}

		// 페이징 처리후 출력
		
		
//      Set<String> uniqueTokens = new HashSet<>(tokens);
//		List<Notes> pageList = pagemapper.findInPage();
        
        
		
		return Map.of(
					"noteList", searchResult
				);
	}
}
