package com.bbs.demo.mapper;

import com.bbs.demo.domain.Files;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFile(Files files);
    List<Files> selectFilesByNoteId(int note_id);
}
