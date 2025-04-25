package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.domain.Boards;
import com.bbs.demo.domain.CommentResponse;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Users;

@Mapper
public interface AdminMapper {
	List<Users> findAllUserByPage(Admin admin);
	List<Boards> findAllBoardByPage(Admin admin);
	List<Notes> findAllNoteByPage(Admin admin);
	List<CommentResponse> findAllCommentByPage(Admin admin);
}
