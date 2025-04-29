package com.bbs.demo.domain;

import java.time.LocalDateTime;

public class Users {
	private int id;
	private String nickName;
	private String email;
	private String password;		// 차후 hash 보안이나 Spring Security 처리 필요
	private LocalDateTime regDate;
	private LocalDateTime lastAccessDate;
	private int userGrade;			// DB에 check 1 ~ 10으로 제한 걸려있음, 나중에 서비스에서 제한할 것
	private String userGradeString;
	
	public String getUserGradeString() {
		return userGradeString;
	}
	public void setUserGradeString(String userGradeString) {
		this.userGradeString = userGradeString;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public LocalDateTime getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(LocalDateTime lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	public int getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}	
	
}
