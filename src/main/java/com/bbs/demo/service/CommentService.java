package com.bbs.demo.service;

import com.bbs.demo.domain.Comments;
import com.bbs.demo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    // 댓글 작성
    public void addComment(Comments comment) {
        commentMapper.insertComment(comment);
    }
    
    public Comments getCommentById(int id) {
        return commentMapper.getCommentById(id);
    }

    public List<Comments> getCommentsByNoteId(int noteId) {
        // 모든 댓글 조회 (대댓글 포함)
        List<Comments> all = commentMapper.getCommentsByNoteId(noteId);
        
        // 부모-자식 매핑
        Map<Integer, Comments> commentMap = new HashMap<>();
        List<Comments> result = new ArrayList<>();
        
        for (Comments comment : all) {
            commentMap.put(comment.getId(), comment);
            if (comment.getCommentId() == null) { // 최상위 댓글
                result.add(comment);
            } else { // 대댓글 (1단계만 허용)
                Comments parent = commentMap.get(comment.getCommentId());
                if (parent != null) {
                    parent.getChildren().add(comment);
                }
            }
        }
        return result;
    }

    // 댓글 삭제
    public void deleteComment(int id) {
        commentMapper.deleteComment(id);
    }
    
    public void updateComment(Comments comment) {
        commentMapper.updateComment(comment);
    }
    
    
}
