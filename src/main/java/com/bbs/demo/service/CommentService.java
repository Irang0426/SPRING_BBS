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

    // 게시글에 달린 모든 댓글 조회
    public List<Comments> getCommentsByNoteId(int noteId) {
        List<Comments> all = commentMapper.getCommentsByNoteId(noteId);
        Map<Integer, Comments> map = new HashMap<>();
        List<Comments> roots = new ArrayList<>();

        for (Comments c : all) {
            map.put(c.getId(), c);
            if (c.getCommentId() == null) { // 최상위 댓글
                roots.add(c);
            } else {
                Comments parent = map.get(c.getCommentId());
                if (parent != null) {
                    parent.getChildren().add(c);
                }
            }
        }
        return roots;
    }

    // 댓글 삭제
    public void deleteComment(int id) {
        commentMapper.deleteComment(id);
    }
    
    public void updateComment(Comments comment) {
        commentMapper.updateComment(comment);
    }
    
    
}
