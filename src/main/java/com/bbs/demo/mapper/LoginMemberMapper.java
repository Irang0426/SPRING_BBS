package com.bbs.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.bbs.demo.domain.Users;

@Mapper
public interface LoginMemberMapper {
	void insert (Users user);
	Users findbyEmail(String email);
	
	 // 🔥 추가: 사용자 ID로 사용자 정보 조회하는 메서드
    Users findById(Integer id);
    
    // 유저 로그인 성공시 최근 접속일 업데이트
    void lastLogin(Integer id);
}
