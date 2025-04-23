package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.CommentRequest;
import com.bbs.demo.domain.CommentResponse;

@Mapper
public interface CommentMapper {
	void insert(CommentRequest params);
	CommentResponse findById(int id);
	void update(CommentRequest params);
	void delete(int id);
	List<CommentResponse> findAllComment(int noteId);
	
	// 댓글 수 카운팅
	int count(int noteId);
}