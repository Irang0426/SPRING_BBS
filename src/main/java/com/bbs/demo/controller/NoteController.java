package com.bbs.demo.controller;

import com.bbs.demo.domain.Boards;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Token;
import com.bbs.demo.mapper.BoardMapper;
import com.bbs.demo.service.CommentService;
import com.bbs.demo.service.FileService;
import com.bbs.demo.service.NoteService;
import com.bbs.demo.service.Tokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/note") // /note로 시작하는 URL 요청을 처리
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private FileService fileService;

    @Autowired
    private BoardMapper boardmapper; // ✅ 게시판 목록 조회용 Mapper 주입

    @Autowired
    private CommentService commentService;

    /**
     * 게시글 목록 조회
     * URL: /note/list
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<Notes> notes = noteService.getList();
        model.addAttribute("notes", notes);
        return "note/list";
    }

    /**
     * 게시글 등록 페이지로 이동
     * URL: /note/register (GET)
     * ✅ 게시판 목록(boardList)을 뷰로 넘겨 드롭다운 출력
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        List<Boards> boardList = boardmapper.findAllBoard(); // ✅ DB에서 게시판 목록 조회
        model.addAttribute("boardList", boardList);         // ✅ 드롭다운에 쓰일 리스트 전달
        model.addAttribute("note", new Notes());            // ✅ 빈 Notes 객체 전달 (폼 바인딩용)
        return "note_register"; // ✅ note_register.html 로 이동 (Thymeleaf 사용 시)
    }

    /**
     * 게시글 등록 처리
     * URL: /note/register (POST)
     * ✅ 선택한 게시판(boardId 포함)으로 글 저장
     */
    @PostMapping("/register")
    public String register(@RequestParam("files") MultipartFile[] files,
                           Notes notes,
                           RedirectAttributes rttr) throws IOException {

        Tokenizer tokenizer = new Tokenizer();
        Token token = new Token();

        noteService.register(notes);                    // ✅ 게시글 DB 저장 (boardId 포함)
        fileService.storeFiles(files, notes.getId());   // ✅ 첨부파일 저장
        rttr.addFlashAttribute("message", "게시글 등록 성공!");

        List<String> tokens = tokenizer.tokenizer(notes.getContent());
        Set<String> uniqueTokens = new HashSet<>(tokens);

        token.setNote_id(notes.getId());
        for (String content : uniqueTokens) {
            token.setContent(content);
            noteService.tokenList(token);               // ✅ 키워드 토큰 저장
        }

        return "redirect:/board/list"; // ✅ 게시판 목록 페이지로 이동
    }

    /**
     * 게시글 상세 조회
     * URL: /note/read?id=1
     */
    @GetMapping("/read")
    public String read(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.get(id));
        model.addAttribute("files", fileService.getAllFilesByNoteId(id));
        model.addAttribute("comments", commentService.getCommentsByNoteId(id));
        return "note_read";
    }

    /**
     * 게시글 수정 페이지 이동
     * URL: /note/modify?id=1 (GET)
     */
    @GetMapping("/modify")
    public String modifyForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.get(id));
        return "note_modify";
    }

    /**
     * 게시글 수정 처리
     * URL: /note/modify (POST)
     */
    @PostMapping("/modify")
    public String modify(Notes notes, RedirectAttributes rttr) {
        Tokenizer tokenizer = new Tokenizer();
        Token token = new Token();

        noteService.modify(notes);
        noteService.deleteTokens(notes.getId());

        List<String> tokens = tokenizer.tokenizer(notes.getContent());
        Set<String> uniqueTokens = new HashSet<>(tokens);

        token.setNote_id(notes.getId());
        for (String content : uniqueTokens) {
            token.setContent(content);
            noteService.tokenList(token);
        }

        rttr.addFlashAttribute("message", "수정 완료!");
        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제 처리
     * URL: /note/remove (POST)
     */
    @PostMapping("/remove")
    public String remove(@RequestParam("id") int id, RedirectAttributes rttr) {
        noteService.remove(id);
        noteService.deleteTokens(id);
        rttr.addFlashAttribute("message", "삭제 완료!");
        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제 처리 (AJAX용)
     * URL: /note/remove2 (POST)
     */
    @PostMapping("/remove2")
    @ResponseBody
    public String removeAjax(@RequestParam("id") int id) {
        noteService.remove(id);
        noteService.deleteTokens(id);
        return "OK";
    }
}
