package com.bbs.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbs.demo.domain.CommentRequest;
import com.bbs.demo.domain.CommentResponse;
import com.bbs.demo.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Transactional
	public int insertComment(final CommentRequest params) {
		commentMapper.insert(params);
		return params.getId();
	}
	
	@Transactional
	public int updateComment(final CommentRequest params) {
		commentMapper.update(params);
		return params.getId();
	}
	
	@Transactional
	public int deleteComment(final int id) {
		commentMapper.delete(id);
		return id;
	}
	
	public CommentResponse findCommentById(final int id) {
        return commentMapper.findById(id);
    }
	
	public List<CommentResponse> findAllComment(final int noteId) {
        return commentMapper.findAllComment(noteId);
    }
}