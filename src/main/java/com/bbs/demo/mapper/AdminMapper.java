package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.domain.Boards;
import com.bbs.demo.domain.Comments;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Users;

@Mapper
public interface AdminMapper {
	List<Users> findAllUserByPage(Admin admin);
	List<Boards> findAllBoardByPage(Admin admin);
	List<Notes> findAllNoteByPage(Admin admin);
	List<Comments> findAllCommentByPage(Admin admin);
	
	void createBoard(String name);
	void updateUserGrade(Users user);
	
	int pageCount(Admin admin);
	
	void deleteUsers(int id);
	void deleteBoards(int id);
	void deleteNotes(int id);
	void deleteComments(int id);
	
	void deleteFilesbyUserId(int userId);
	void deleteViewCountbyUserId(int userId);
	void deleteNoteTokenbyUserId(int userId);
	void deleteNoteCommentsbyUserId(int userId);
	void deleteNotesbyUserId(int userId);
	void deleteCommentsbyUserId(int userId);
	
	void deleteFilesbyBoardId(int boardId);
	void deleteViewCountbyBoardId(int boardId);
	void deleteNoteTokenbyBoardId(int boardId);
	void deleteCommentsbyBoardId(int boardId);
	void deleteNotesbyBoardId(int boardId);
	
	void deleteFiles(int noteId);
	void deleteViewCount(int noteId);
	void deleteNoteToken(int noteId);
	void deleteCommentsbyNoteId(int noteId);
	
	void deleteCommentsbyParentId(int commentId);
}
