package com.bbs.demo.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Page;
import com.bbs.demo.mapper.AdminMapper;
import com.bbs.demo.mapper.PageMapper;

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
    
    private String shortContent(String content) {
    	if (content == null) {
    		content = "";
    	}
    	String shortContent = content.length() > 20 ? content.substring(0,20)+"..." : content;
    	return shortContent;
    }
    
    public String createBoard(Map<String, String> params) {
        Admin admin = createPageCondition(params);
        System.out.println("\t\t\t\tService params: "+params);
	    String boardName = (String) params.get("boardName");
	    adminMapper.createBoard(boardName);
	    
	    System.out.println("page="+admin.getPage()+"&limit="+admin.getLimit()+"&order="+admin.getOrderby()+"&sort="+admin.getSortby()+"&url="+admin.getUrl());
	    return "page="+admin.getPage()+"&limit="+admin.getLimit()+"&order="+admin.getOrderby()+"&sort="+admin.getSortby()+"&url="+admin.getUrl();
    }
    
    public Object getUsers(Map<String, String> params) {
        return adminMapper.findAllUserByPage(createPageCondition(params));
    }

    public Object getBoards(Map<String, String> params) {
        return adminMapper.findAllBoardByPage(createPageCondition(params));
    }

    public Object getNotes(Map<String, String> params) {
    	List<Notes> notes = adminMapper.findAllNoteByPage(createPageCondition(params));
    	for (int i=0; i< notes.size(); i++) {
    		notes.get(i).setContent(shortContent(notes.get(i).getContent()));
    	}
        return notes;
    }

    public Object getComments(Map<String, String> params) {
        return adminMapper.findAllCommentByPage(createPageCondition(params));
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
		System.out.println("Files 삭제 완료");
		adminMapper.deleteViewCountbyBoardId(id);
		System.out.println("ViewCount 삭제 완료");
		adminMapper.deleteNoteTokenbyBoardId(id);
		System.out.println("NoteToken 삭제 완료");
		adminMapper.deleteCommentsbyBoardId(id);
		System.out.println("Comments 삭제 완료");
		adminMapper.deleteNotesbyBoardId(id);
		System.out.println("Notes 삭제 완료");
    	adminMapper.deleteBoards(id);
		System.out.println("Boards 삭제 완료");
    }

	@Transactional
    public void deleteNotes(int id) {
		adminMapper.deleteFiles(id);
		adminMapper.deleteViewCount(id);
		adminMapper.deleteNoteToken(id);
    	adminMapper.deleteCommentsbyNoteId(id);
    	adminMapper.deleteNotes(id);
    }

	@Transactional
    public void deleteComments(int id) {
		adminMapper.deleteCommentsbyParentId(id);
    	adminMapper.deleteComments(id);
    }
}