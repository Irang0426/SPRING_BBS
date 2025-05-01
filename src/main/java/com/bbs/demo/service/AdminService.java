package com.bbs.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.domain.Boards;
import com.bbs.demo.domain.Comments;
import com.bbs.demo.domain.Files;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Users;
import com.bbs.demo.mapper.AdminMapper;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin createPageCondition(Map<String, String> params) {
        Admin admin = new Admin()
            .setOrderby(params.getOrDefault("orderby", "asc"))
            .setSortby(params.getOrDefault("sortby", "id"))
            .setPage(Integer.parseInt(params.getOrDefault("page", "0")))
            .setLimit(Integer.parseInt(params.getOrDefault("limit", "20")))
            .setUrl(params.getOrDefault("url", "users"))
            .setOffset();
        return admin.setTotalPageCount(adminMapper.pageCount(admin));
    }
    
    private String shortContent(String content, int limit) {
    	if (content == null) {
    		content = "";
    	}
    	String shortContent = content.length() > limit ? content.substring(0,limit)+"..." : content;
    	return shortContent;
    }
    
    private Admin isUniqueColumn(Map<String, String> params) {
    	Admin admin = createPageCondition(params);
    	if (adminMapper.recodeCount(admin) <= 1) {
    		return admin.setOrderby("asc");
    	}
    	return admin;
    }
    
    public String createBoard(Map<String, String> params) {
        Admin admin = createPageCondition(params);
	    String boardName = (String) params.get("boardName");
	    adminMapper.createBoard(boardName);
	    
	    return "page="+admin.getPage()+"&limit="+admin.getLimit()+"&order="+admin.getOrderby()+"&sort="+admin.getSortby()+"&url="+admin.getUrl();
    }
    
    public Object getUsers(Map<String, String> params) {
    	List<Users> users = adminMapper.findAllUserByPage(isUniqueColumn(params));
    	for (int i=0; i < users.size(); i++) {
    		users.get(i).setNickName(shortContent(users.get(i).getNickName(),15));
    	}
        return users; 
    }

    public Object getBoards(Map<String, String> params) {
    	List<Boards> boards = adminMapper.findAllBoardByPage(isUniqueColumn(params));
    	for (int i=0; i < boards.size(); i++) {
    		boards.get(i).setName(shortContent(boards.get(i).getName(),15));
    	}
        return boards;
    }

    public Object getNotes(Map<String, String> params) {
    	List<Notes> notes = adminMapper.findAllNoteByPage(isUniqueColumn(params));
    	
    	for (int i=0; i< notes.size(); i++) {
    		notes.get(i).setTitle(shortContent(notes.get(i).getTitle(),12));
    		notes.get(i).setContent(shortContent(notes.get(i).getContent(),12));
    	}
        return notes;
    }

    public Object getComments(Map<String, String> params) {
    	List<Comments> comments = adminMapper.findAllCommentByPage(isUniqueColumn(params));
    	for (int i=0; i < comments.size(); i++) {
    		comments.get(i).setContent(shortContent(comments.get(i).getContent(),10));
    	}
        return comments;
    }

    public Object getFiles(Map<String, String> params) {
    	List<Files> files = adminMapper.findAllFileByPage(isUniqueColumn(params));
    	for (int i=0; i < files.size(); i++) {
    		files.get(i).setFilename(shortContent(files.get(i).getFilename(),15));
    	}
        return files;
    }
    
    public void updateUserGrade(int id, int newGrade) {
    	Users user = new Users();
    	user.setId(id);
    	user.setUserGrade(newGrade);
    	adminMapper.updateUserGrade(user);
    }
    
	@Transactional
    public void deleteUsers(int id) {
		adminMapper.deleteFilesbyUserId(id);
		adminMapper.deleteViewCountbyUserId(id);
		adminMapper.deleteNoteTokenbyUserId(id);
		adminMapper.deleteNoteCommentsbyUserId(id);
		adminMapper.deleteNotesbyUserId(id);
		adminMapper.deleteCommentsbyUserId(id);
    	adminMapper.deleteUsers(id);
    }

	@Transactional
    public void deleteBoards(int id) {
		adminMapper.deleteFilesbyBoardId(id);
		adminMapper.deleteViewCountbyBoardId(id);
		adminMapper.deleteNoteTokenbyBoardId(id);
		adminMapper.deleteCommentsbyBoardId(id);
		adminMapper.deleteNotesbyBoardId(id);
    	adminMapper.deleteBoards(id);
    }

	@Transactional
    public void deleteNotes(int id) {
		adminMapper.deleteFilesbyNoteId(id);
		adminMapper.deleteViewCountbyNoteId(id);
		adminMapper.deleteNoteTokenbyNoteId(id);
    	adminMapper.deleteCommentsbyNoteId(id);
    	adminMapper.deleteNotes(id);
    }

	@Transactional
    public void deleteComments(int id) {
		adminMapper.deleteCommentsbyParentId(id);
    	adminMapper.deleteComments(id);
    }

	@Transactional
    public void deleteFiles(int id) {
    	adminMapper.deleteFiles(id);
    }
}