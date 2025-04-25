package com.bbs.demo.service;

import java.util.List;

import com.bbs.demo.domain.NoteDTO;

public interface NoteService {

	List<NoteDTO> getList();

	void register(NoteDTO noteDTO);

	Object get(int id);

	void modify(NoteDTO noteDTO);

	void remove(int id);

}
