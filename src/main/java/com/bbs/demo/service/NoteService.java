package com.bbs.demo.service;

import java.util.List;

import com.bbs.demo.domain.Notes; // ✅ NoteDTO → Notes로 수정
import com.bbs.demo.domain.Token;

public interface NoteService {

    List<Notes> getList(); // ✅ NoteDTO → Notes

    void register(Notes notes); // ✅ NoteDTO → Notes

    Notes get(int id); // ✅ Object → Notes 로 변경 추천 (타입 명확화)

    void modify(Notes notes); // ✅ NoteDTO → Notes

    void remove(int id);

    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
    void tokenList(Token token);
    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
}
