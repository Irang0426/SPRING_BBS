package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Admin;
import com.bbs.demo.domain.Boards;

@Mapper
public interface AdminMapper {
	List<Boards> findAllBoardByPage(Admin admin);
}
