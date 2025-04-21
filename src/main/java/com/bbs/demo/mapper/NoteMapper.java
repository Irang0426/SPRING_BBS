package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Note;

@Mapper
public interface NoteMapper {
	List<Note> findAllNote();
	List<Note> findByBoardId(int id);
	Note findByNoteId(int id);
}
