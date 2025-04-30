package com.bbs.demo.controller;

import com.bbs.demo.domain.Boards;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Token;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.BoardMapper;
import com.bbs.demo.mapper.LoginMemberMapper; // ✅ 작성자 닉네임 조회용 Mapper 추가

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
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private FileService fileService;

    @Autowired
    private BoardMapper boardmapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LoginMemberMapper loginMemberMapper; // ✅ 작성자 조회용 Mapper 주입

    @GetMapping("/list")
    public String list(Model model) {
        List<Notes> notes = noteService.getList();
        model.addAttribute("notes", notes);
        return "note/list";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        List<Boards> boardList = boardmapper.findAllBoard();
        model.addAttribute("boardList", boardList);
        model.addAttribute("note", new Notes());
        return "note_register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("files") MultipartFile[] files,
                           Notes notes,
                           RedirectAttributes rttr) throws IOException {

        Tokenizer tokenizer = new Tokenizer();
        Token token = new Token();

        noteService.register(notes);
        fileService.storeFiles(files, notes.getId());
        rttr.addFlashAttribute("message", "게시글 등록 성공!");

        List<String> tokens = tokenizer.tokenizer(notes.getContent());
        Set<String> uniqueTokens = new HashSet<>(tokens);

        token.setNote_id(notes.getId());
        for (String content : uniqueTokens) {
            token.setContent(content);
            noteService.tokenList(token);
        }

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("id") int id, Model model) {
        Notes note = noteService.get(id);
        model.addAttribute("note", note);
        model.addAttribute("files", fileService.getAllFilesByNoteId(id));
        model.addAttribute("comments", commentService.getCommentsByNoteId(id));

        // 🔥 작성자의 닉네임을 가져와서 모델에 추가
        Users writer = loginMemberMapper.findById(note.getUserId());
        model.addAttribute("nickname", writer.getNickName());

        return "note_read";
    }


    @GetMapping("/modify")
    public String modifyForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.get(id));
        return "note_modify";
    }

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

    @PostMapping("/remove")
    public String remove(@RequestParam("id") int id, RedirectAttributes rttr) {
        noteService.remove(id);
        noteService.deleteTokens(id);
        rttr.addFlashAttribute("message", "삭제 완료!");
        return "redirect:/board/list";
    }

    @PostMapping("/remove2")
    @ResponseBody
    public String removeAjax(@RequestParam("id") int id) {
        noteService.remove(id);
        noteService.deleteTokens(id);
        return "OK";
    }
}
