package com.bbs.demo.mapper;

import com.bbs.demo.domain.NoteDTO;
import com.bbs.demo.domain.Notes;
import com.bbs.demo.domain.Token;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {
	List<Notes> findAllNote();
	List<Notes> findByBoardId(int id);
	Notes findByNoteId(int id);

    // 전체 게시글 목록 조회
    List<NoteDTO> getList();

    // 게시글 등록
    void insert(NoteDTO noteDTO);

    // 게시글 한 건 조회
    NoteDTO select(int id);

    // 게시글 수정
    void update(NoteDTO noteDTO);

    // 게시글 삭제
    void delete(int id);
    
///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
    void tokenList(Token token);
///////////////////////////////////////////// 임의 수정 //////////////////////////////////////////////////////////
}
