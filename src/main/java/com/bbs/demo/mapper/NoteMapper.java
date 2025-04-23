package com.bbs.demo.mapper;

import com.bbs.demo.domain.NoteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {

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
}
