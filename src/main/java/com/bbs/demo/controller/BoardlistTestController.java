/*
 * // 보트 리스트 출력 전용 테스트 파일입니다.
 * 
 * package com.bbs.demo.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody;
 * 
 * import com.bbs.demo.domain.Notes; import com.bbs.demo.domain.Page; import
 * com.bbs.demo.mapper.BoardMapper; import com.bbs.demo.mapper.NoteMapper;
 * 
 * @Controller
 * 
 * @RequestMapping("/listtest") public class BoardlistTestController {
 * 
 * @Autowired private BoardMapper boardmapper;
 * 
 * @Autowired private NoteMapper notemapper;
 * 
 * @GetMapping("/boardlisttest") public String boardList(Model model) { Page
 * page = new Page();
 * 
 * model.addAttribute("boardList", boardmapper.findAllBoard());
 * model.addAttribute("pageInfo", page);
 * 
 * return "boardlisttest"; }
 * 
 * @PostMapping("/notelisttest")
 * 
 * @ResponseBody public List<Notes> noteListByBoardId(@RequestParam(name =
 * "boardId") String id , Model model) { System.out.println("GET FETCH!");
 * List<Notes> notelist = notemapper.findByBoardId(Integer.parseInt(id)); return
 * notelist; }
 * 
 * }
 */
