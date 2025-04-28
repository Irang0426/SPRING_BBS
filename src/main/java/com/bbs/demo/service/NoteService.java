package com.bbs.demo.service;

import java.util.List;

import com.bbs.demo.domain.NoteDTO;
import com.bbs.demo.domain.Token;

public interface NoteService {

	List<NoteDTO> getList();

	void register(NoteDTO noteDTO);

	Object get(int id);

	void modify(NoteDTO noteDTO);

	void remove(int id);

///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
	void tokenList(Token token);
///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
}
