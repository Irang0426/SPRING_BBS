package com.bbs.demo.controller;

import com.bbs.demo.domain.NoteDTO;
import com.bbs.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/note") // /note로 시작하는 URL 요청을 처리
public class NoteController {

    @Autowired
    private NoteService noteService;

    /**
     * 게시글 목록 조회
     * URL: /note/list
     * View: note/list.jsp
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<NoteDTO> notes = noteService.getList();
        model.addAttribute("notes", notes); // JSP에서 ${notes}로 접근 가능
        return "note/list";
    }

    /**
     * 게시글 등록 페이지로 이동
     * URL: /note/register (GET)
     * View: note/register.jsp
     */
    @GetMapping("/register")
    public String registerForm() {
        return "note_register";
    }

    /**
     * 게시글 등록 처리
     * URL: /note/register (POST)
     * 처리 후 /note/list로 리다이렉트
     */
    @PostMapping("/register")
    public String register(NoteDTO noteDTO, RedirectAttributes rttr) {
        noteService.register(noteDTO);
        rttr.addFlashAttribute("message", "게시글 등록 성공!");
        return "redirect:/note/read?id=";
    }

    /**
     * 게시글 상세 조회
     * URL: /note/read?id=1
     * View: note/read.jsp
     */
    @GetMapping("/read")
    public String read(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.get(id)); // noteDTO 한 건
        return "note_read";
    }

    /**
     * 게시글 수정 페이지 이동
     * URL: /note/modify?id=1 (GET)
     * View: note/modify.jsp
     */
    @GetMapping("/modify")
    public String modifyForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.get(id));
        return "note_modify";
    }

    /**
     * 게시글 수정 처리
     * URL: /note/modify (POST)
     * 처리 후 /note/list로 리다이렉트
     */
    @PostMapping("/modify")
    public String modify(NoteDTO noteDTO, RedirectAttributes rttr) {
        noteService.modify(noteDTO);
        rttr.addFlashAttribute("message", "수정 완료!");
        return "redirect:/note/list";
    }

    /**
     * 게시글 삭제 처리
     * URL: /note/remove (POST)
     * 처리 후 /note/list로 리다이렉트
     */
    @PostMapping("/remove")
    public String remove(@RequestParam("id") int id, RedirectAttributes rttr) {
        noteService.remove(id);
        rttr.addFlashAttribute("message", "삭제 완료!");
        return "redirect:/note/list";
    }
}
