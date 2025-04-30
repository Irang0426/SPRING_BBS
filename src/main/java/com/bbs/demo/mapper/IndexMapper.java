package com.bbs.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Users;

@Mapper
public interface IndexMapper {
	public int userCount();
	public int noteCount();
	public int fileCount();
	public int fileNoteCount();
	public int commentCount();
	public Users newUser(int count);
	public List<Notes> newNotes(int count);
}
