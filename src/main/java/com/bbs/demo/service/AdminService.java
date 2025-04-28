package com.bbs.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.mapper.AdminMapper;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private Admin createPageCondition(Map<String, String> params) {
        return new Admin()
            .setOrderby(params.getOrDefault("orderby", "asc"))
            .setSortby(params.getOrDefault("sortby", "id"))
            .setPage(Integer.parseInt(params.getOrDefault("page", "0")))
            .setLimit(Integer.parseInt(params.getOrDefault("limit", "20")));
    }
    
    public String setRedirectUrl(Map<String, String> params) {
        StringBuilder redirectUrl = new StringBuilder("redirect:/admin");

        if (!params.isEmpty()) {
            redirectUrl.append("?");
            params.forEach((key, value) -> redirectUrl.append(key).append("=").append(value).append("&"));
            // 마지막 '&' 삭제
            redirectUrl.deleteCharAt(redirectUrl.length() - 1);
        }

        return redirectUrl.toString();
    }

    public Object getUsers(Map<String, String> params) {
        return adminMapper.findAllUserByPage(createPageCondition(params));
    }

    public Object getBoards(Map<String, String> params) {
        return adminMapper.findAllBoardByPage(createPageCondition(params));
    }

    public Object getNotes(Map<String, String> params) {
        return adminMapper.findAllNoteByPage(createPageCondition(params));
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
		adminMapper.deleteViewCountbyBoardId(id);
		adminMapper.deleteNoteTokenbyBoardId(id);
		adminMapper.deleteCommentsbyBoardId(id);
		adminMapper.deleteNotesbyBoardId(id);
    	adminMapper.deleteBoards(id);
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