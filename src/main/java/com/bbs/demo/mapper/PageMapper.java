package com.bbs.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Page;

@Mapper
public interface PageMapper {
	List<Notes> findInPage(Page pageDate);
	int pageCount(int boardPos);
	List<Notes> noteList(Map<String, Object> params);
}
