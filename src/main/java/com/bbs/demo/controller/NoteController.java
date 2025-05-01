package com.bbs.demo.controller;

import com.bbs.demo.domain.Boards;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Token;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.BoardMapper;
import com.bbs.demo.mapper.LoginMemberMapper;
import com.bbs.demo.service.CommentService;
import com.bbs.demo.service.FileService;
import com.bbs.demo.service.NoteService;
import com.bbs.demo.service.Tokenizer;

import jakarta.servlet.http.HttpSession; // ✅ 세션 사용을 위한 import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired private NoteService noteService;
    @Autowired private FileService fileService;
    @Autowired private BoardMapper boardmapper;
    @Autowired private CommentService commentService;
    @Autowired private LoginMemberMapper loginMemberMapper; // ✅ 작성자 닉네임 조회용 Mapper

    @GetMapping("/list")
    public String list(Model model) {
        List<Notes> notes = noteService.getList();
        model.addAttribute("notes", notes);
        return "note/list";
    }

    @GetMapping("/register")
    public String registerForm(@RequestParam(name="boardId") int boardId ,Model model) {
        List<Boards> boardList = boardmapper.findAllBoard();
        model.addAttribute("boardList", boardList);
        model.addAttribute("note", new Notes());
        model.addAttribute("nowBoard", boardId);
        return "note_register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("files") MultipartFile[] files,
                           Notes notes,
                           HttpSession session, // ✅ 세션에서 로그인 유저 확인
                           RedirectAttributes rttr) throws IOException {

        Users loginUser = (Users) session.getAttribute("loginMember"); // ✅ 세션에서 유저 꺼냄
        if (loginUser == null) {
            return "redirect:/login";
        }

        notes.setUserId(loginUser.getId()); // ✅ 작성자 ID 삽입

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

        // ✅ 작성자의 닉네임 조회 후 모델에 전달
        Users writer = loginMemberMapper.findById(note.getUserId());
        model.addAttribute("nickname", writer != null ? writer.getNickName() : "탈퇴한 사용자");

        return "note_read";
    }

    @GetMapping("/modify")
    public String modifyForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.get(id));
        model.addAttribute("files", fileService.getAllFilesByNoteId(id));
        return "note_modify";
    }

    @PostMapping("/modify")
    public String modify(@RequestParam("files") MultipartFile[] files,
                         @RequestParam(value = "remainingFileIds", required = false) List<Integer> remainingFileIds,
                         Notes notes, RedirectAttributes rttr) throws IOException {
        noteService.modify(notes); // 게시글 내용 수정

        // ✅ 기존 파일 중 삭제된 것 제거
        fileService.removeDeletedFiles(notes.getId(), remainingFileIds);

        // ✅ 새 파일 저장
        fileService.storeFiles(files, notes.getId());

        // ✅ 키워드 재추출
        noteService.deleteTokens(notes.getId());
        Tokenizer tokenizer = new Tokenizer();
        Token token = new Token();

        List<String> tokens = tokenizer.tokenizer(notes.getContent());
        Set<String> uniqueTokens = new HashSet<>(tokens);

        token.setNote_id(notes.getId());
        for (String content : uniqueTokens) {
            token.setContent(content);
            noteService.tokenList(token);
        }

        rttr.addFlashAttribute("message", "수정 완료!");
        return "redirect:/note/read?id=" + notes.getId();
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

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> displayImage(@PathVariable("id") Integer id) {
        com.bbs.demo.domain.Files file = fileService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        String filename = file.getFilename().toLowerCase();
        MediaType mediaType;

        if (filename.endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG;
        } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (filename.endsWith(".gif")) {
            mediaType = MediaType.IMAGE_GIF;
        } else {
            // 이미지가 아닌 경우 반환하지 않음 (보안상)
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename*=UTF-8''" +
                        URLEncoder.encode(file.getFilename(), StandardCharsets.UTF_8).replaceAll("\\+", "%20"))
                .body(file.getFiles());
    }
}
