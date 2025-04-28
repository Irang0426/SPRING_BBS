package com.bbs.demo.mapper;

import com.bbs.demo.domain.Notes; // ✅ NoteDTO import 삭제, Notes import
import com.bbs.demo.domain.Token;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface NoteMapper {

    List<Notes> findAllNote();
    List<Notes> findByBoardId(int id);
    Notes findByNoteId(int id);

    // 전체 게시글 목록 조회
    List<Notes> getList(); // ✅ 수정: NoteDTO → Notes

    // 게시글 등록
    void insert(Notes notes); // ✅ 수정: NoteDTO → Notes

    // 게시글 한 건 조회
    Notes select(int id); // ✅ 수정: NoteDTO → Notes

    // 게시글 수정
    void update(Notes notes); // ✅ 수정: NoteDTO → Notes

    // 게시글 삭제
    void delete(int id);

    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
    void tokenList(Token token);
    void deleteTokens(int noteId);
    ///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
}
