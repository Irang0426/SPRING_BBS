package com.bbs.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbs.demo.domain.CommentRequest;
import com.bbs.demo.domain.CommentResponse;
import com.bbs.demo.service.CommentService;

@RestController
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;
	
	// 신규 댓글 생성
	@PostMapping("/posts/{noteId}/comments")
	public CommentResponse insertComment(@PathVariable int noteId, @RequestBody CommentRequest params) {
		int id = commentService.insertComment(params);
		return commentService.findCommentById(id);
	}
	
	// 댓글 리스트 조회
	@GetMapping("/posts/{noteId}/comments")
	public List<CommentResponse> findAllComment(@PathVariable int noteId){
		return commentService.findAllComment(noteId);
	}
	
	// 댓글 상세정보 조회
    @GetMapping("/posts/{noteId}/comments/{id}")
    public CommentResponse findCommentById(@PathVariable int noteId, @PathVariable int id) {
        return commentService.findCommentById(id);
    }


//    // 기존 댓글 수정
//    @PatchMapping("/posts/{postId}/comments/{id}")
//    public CommentResponse updateComment(@PathVariable final Long postId, @PathVariable final Long id, @RequestBody final CommentRequest params) {
//        commentService.updateComment(params);
//        return commentService.findCommentById(id);
//    }
}