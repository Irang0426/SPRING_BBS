package com.bbs.demo.domain;

import java.awt.Image;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {
	private int id;
	private String content;
	private Image image;
	private int parentCommentId;		// 대댓글인 경우 부모들의 id / 대댓글이 아니면 Null
	private int noteId;
	private int commentAuthorId;		// 작성자
	
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(int parentCommentId) {
		this.parentCommentId = parentCommentId;
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
}