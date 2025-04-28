package com.bbs.demo.mapper;

import com.bbs.demo.domain.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileUploadMapper {
    void insertFile(Files files);
}
