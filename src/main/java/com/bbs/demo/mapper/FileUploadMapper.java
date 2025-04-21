package com.bbs.demo.mapper;

import com.bbs.demo.domain.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileUploadMapper {
    void insertFile(File file);
}
