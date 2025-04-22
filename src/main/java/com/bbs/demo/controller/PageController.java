package com.bbs.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Page;
import com.bbs.demo.mapper.PageMapper;

@Controller
@RequestMapping("/pagingtest")
public class PageController {

	@Autowired
	PageMapper pagemapper;
	
	@GetMapping("/page")
	public String PageList(@RequestParam(name="boardPos") int boardPos,
							@RequestParam(name="pagePos") int pagePos,
							Model model) {
		Page page = new Page();
		int pagecount = pagemapper.pageCount();
		if(pagecount % page.getPageSize() != 0) {
			pagecount = (int)(pagecount / page.getPageSize()) + 1;
		}
		else {
			pagecount = (int)(pagecount / page.getPageSize());
		}
		
		page.setBoardPos(boardPos);
		page.setPage(pagePos);
		page.setPageLen(pagecount);
		
		List<Notes> pageList = pagemapper.findInPage(page);
		model.addAttribute("boardList", pageList);
		model.addAttribute("pageInfo", page);
		
		return "boardlisttest";
	}
}
