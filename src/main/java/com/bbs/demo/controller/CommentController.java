package com.bbs.demo.controller;

import com.bbs.demo.domain.Comments;
import com.bbs.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 게시글에 달린 댓글 조회
    @GetMapping("/list")
    public String getComments(@RequestParam("noteId") int noteId, Model model) {
        List<Comments> comments = commentService.getCommentsByNoteId(noteId);
        model.addAttribute("comments", comments);
        return "comment_test";  // 댓글을 표시할 게시글 상세 페이지 (예시)
    }

    // 댓글 작성
    @PostMapping("/add")
    public String addComment(@ModelAttribute Comments comment) {
        // 부모 댓글이 존재하는지 확인
        if (comment.getCommentId() != null) {
            Comments parent = commentService.getCommentById(comment.getCommentId());
            if (parent == null) {
                throw new IllegalArgumentException("부모 댓글이 존재하지 않습니다.");
            }
        }
        commentService.addComment(comment);
        return "redirect:/comments/list?noteId=" + comment.getNoteId();
    }

    // 댓글 삭제
    @PostMapping("/delete")
    public String deleteComment(@RequestParam("id") int id, @RequestParam("noteId") int noteId) {
        commentService.deleteComment(id);
        return "redirect:/comments/list?noteId=" + noteId;
    }
    
    @PostMapping("/update")
    public String updateComment(@ModelAttribute Comments comment) {
        commentService.updateComment(comment);
        return "redirect:/comments/list?noteId=" + comment.getNoteId();
    }
}