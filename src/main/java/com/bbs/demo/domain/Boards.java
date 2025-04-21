package com.bbs.demo.domain;

import java.time.LocalDateTime;

public class Boards {
	private Integer id;
	private String name;
	private LocalDateTime create_date;
	private LocalDateTime last_modified_date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
