package com.bbs.demo.service;

import com.bbs.demo.domain.NoteDTO;
import com.bbs.demo.domain.Token;
import com.bbs.demo.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // ⭐ 꼭 필요!
public class NoteServiceImpl implements NoteService {  // ⭐ 인터페이스 구현!

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteDTO> getList() {
        return noteMapper.getList();
    }

    @Override
    public void register(NoteDTO noteDTO) {
        noteMapper.insert(noteDTO);
    }

    @Override
    public NoteDTO get(int id) {
        return noteMapper.select(id);
    }

    @Override
    public void modify(NoteDTO noteDTO) {
        noteMapper.update(noteDTO);
    }

    @Override
    public void remove(int id) {
        noteMapper.delete(id);
    }
    
    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
    @Override
    public void tokenList(Token token) {
    	noteMapper.tokenList(token);
    }
    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
}
