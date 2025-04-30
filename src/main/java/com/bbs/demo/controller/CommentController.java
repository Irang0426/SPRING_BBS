package com.bbs.demo.controller;

import com.bbs.demo.domain.Comments;
import com.bbs.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    

    // ✅ 댓글 목록 JSON 반환
    @GetMapping("/json")
    @ResponseBody
    public List<Comments> getCommentsJson(@RequestParam("noteId") int noteId) {
        List<Comments> comments = commentService.getCommentsByNoteId(noteId);
    	return comments;
    }

    // ✅ 댓글 등록 (AJAX)
    @PostMapping("/ajax/add")
    @ResponseBody
    public ResponseEntity<?> addCommentAjax(@ModelAttribute Comments comment,
                                            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            comment.setImages(imageFile.getBytes());
        } else {
            comment.setImages(null);
        }

        if (comment.getCommentId() != null) {
            Comments parent = commentService.getCommentById(comment.getCommentId());
            if (parent == null) {
                return ResponseEntity.badRequest().body("부모 댓글이 존재하지 않습니다.");
            }
        }

        Comments saved = commentService.addComment(comment);
        return ResponseEntity.ok(saved);
    }

    // ✅ 댓글 수정 (AJAX)
    @PostMapping("/ajax/update")
    @ResponseBody
    public ResponseEntity<?> updateCommentAjax(@RequestParam("id") int id,
                                               @RequestParam("content") String content,
                                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        // 댓글을 DB에서 가져오기
        Comments comment = commentService.getCommentById(id);
        if (comment != null) {
            // 수정할 댓글 내용 설정
            comment.setContent(content);

            // 이미지가 있으면 업데이트
            if (imageFile != null && !imageFile.isEmpty()) {
                comment.setImages(imageFile.getBytes());
            }

            // 댓글 업데이트
            commentService.updateComment(comment);
            return ResponseEntity.ok(comment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 찾을 수 없습니다.");
        }
    }

    // ✅ 댓글 삭제 (AJAX)
    @PostMapping("/ajax/delete")
    @ResponseBody
    public ResponseEntity<?> deleteCommentAjax(@RequestParam("id") int id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("삭제 성공");
    }

    // ✅ 댓글 이미지 반환
    @GetMapping("/images")
    public ResponseEntity<byte[]> getCommentImage(@RequestParam("id") int id) {
        Comments comment = commentService.getCommentById(id);
        byte[] imageData = comment.getImages();

        if (imageData == null || imageData.length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
