package com.bbs.demo.domain;

import java.time.LocalDateTime;

public class NoteDTO {
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	private int id;                      // 게시글 고유 번호
    private String title;                // 게시글 제목
    private String content;              // 게시글 본문
    private LocalDateTime createDate;    // 작성일
    private LocalDateTime lastModifiedDate; // 마지막 수정일
    private int boardId;                 // 소속 게시판 ID (foreign key)
    private int userId;                  // 작성자 ID (foreign key)
}
