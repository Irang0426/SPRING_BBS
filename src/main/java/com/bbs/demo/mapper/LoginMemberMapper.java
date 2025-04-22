package com.bbs.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.bbs.demo.domain.Users;

@Mapper
public interface LoginMemberMapper {
	void insert (Users user);
	Users findloginid(String loginid);
}
