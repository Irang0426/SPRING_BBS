package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Notes;

@Mapper
public interface NoteMapper {
	List<Notes> findAllNote();
	List<Notes> findByBoardId(int id);
	Notes findByNoteId(int id);
}
