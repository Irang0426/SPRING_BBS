package com.bbs.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Page;

@Mapper
public interface PageMapper {
	List<Notes> findAllPage(Page pageDate);
	int pageAllCount();
	List<Notes> findInPage(Page pageDate);
	int pageCount(int boardPos);
	List<Notes> noteList(Map<String, Object> params);
	List<Notes> noteAllList(Map<String, Object> params);
}
