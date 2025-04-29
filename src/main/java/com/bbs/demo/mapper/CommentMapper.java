package com.bbs.demo.mapper;

import com.bbs.demo.domain.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 댓글 작성
    void insertComment(Comments comment);

    // 게시글에 달린 댓글 조회 (대댓글도 포함)
    List<Comments> getCommentsByNoteId(@Param("noteId") int noteId);

    // 특정 댓글 조회 (대댓글 관계 확인을 위해)
    Comments getCommentById(@Param("id") int id);

    // 댓글 삭제
    void deleteComment(@Param("id") int id);
    
    void deleteParentsComment(@Param("id") int id);
    
    void updateComment(Comments comment);
}
