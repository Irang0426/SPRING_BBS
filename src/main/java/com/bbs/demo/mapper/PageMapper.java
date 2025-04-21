package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Note;
import com.bbs.demo.domain.Page;

@Mapper
public interface PageMapper {
	List<Note> findInPage(Page pageDate);
	int pageCount(Page pageData);
}
