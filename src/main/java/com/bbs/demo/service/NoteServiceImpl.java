package com.bbs.demo.service;

import com.bbs.demo.domain.Notes; // ✅ NoteDTO → Notes
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
    public List<Notes> getList() { // ✅ NoteDTO → Notes
        return noteMapper.getList();
    }

    @Override
    public void register(Notes notes) { // ✅ NoteDTO → Notes
        noteMapper.insert(notes);
    }

    @Override
    public Notes get(int id) { // ✅ NoteDTO → Notes
        return noteMapper.select(id);
    }

    @Override
    public void modify(Notes notes) { // ✅ NoteDTO → Notes
        noteMapper.update(notes);
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
    
    @Override
    public void deleteTokens(int noteId) {
    	noteMapper.deleteTokens(noteId);
    }
    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
}
