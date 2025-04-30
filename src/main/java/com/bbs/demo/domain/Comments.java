package com.bbs.demo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comments {
	
	private List<Comments> children = new ArrayList<>();
	
    private int id;  // 댓글 ID
    private String content;  // 댓글 내용
    private LocalDateTime createDate;  // 댓글 생성일
    private LocalDateTime lastModifiedDate;  // 댓글 수정일
    private byte[] images;  // 댓글에 첨부된 이미지 (옵션)
    private Integer commentId;  // 대댓글의 부모 댓글 ID (없으면 0 또는 null로 처리)
    private int noteId;  // 댓글이 달린 게시글 ID
    private int commentAuthorId;  // 댓글 작성자 ID
    private String AuthorNickName;

    public String getAuthorNickName() {
		return AuthorNickName;
	}

	public void setAuthorNickName(String authorNickName) {
		AuthorNickName = authorNickName;
	}

	private Comments parentComment;  // 대댓글을 위한 부모 댓글
    
    // Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public byte[] getImages() {
		return images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public int getCommentAuthorId() {
		return commentAuthorId;
	}

	public void setCommentAuthorId(int commentAuthorId) {
		this.commentAuthorId = commentAuthorId;
	}

	public Comments getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comments parentComment) {
		this.parentComment = parentComment;
	}
	
	public List<Comments> getChildren() { return children; }
	
	public void setChildren(List<Comments> children) { this.children = children; }
}
