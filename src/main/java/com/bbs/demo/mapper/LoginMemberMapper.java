package com.bbs.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.bbs.demo.domain.Users;

@Mapper
public interface LoginMemberMapper {
	void insert (Users user);
	Users findbyEmail(String email);
	
	 // 🔥 추가: 사용자 ID로 사용자 정보 조회하는 메서드
    Users findById(Integer id);
}
