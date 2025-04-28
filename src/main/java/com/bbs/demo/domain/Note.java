package com.bbs.demo.domain;

import java.time.LocalDateTime;

public class Note {
	private Integer id;
	private String title;
	private String content;
	private LocalDateTime create_date;
	private LocalDateTime last_modified_date;
	private Integer board_id;
	private Integer writer_id;
	private Integer file_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public LocalDateTime getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}
	public LocalDateTime getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(LocalDateTime last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public Integer getBoard_id() {
		return board_id;
	}
	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
	}
	public Integer getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(Integer writer_id) {
		this.writer_id = writer_id;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	
	
}
