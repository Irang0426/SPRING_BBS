package com.bbs.demo.service;

import com.bbs.demo.domain.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    void storeFiles(MultipartFile[] multipartFile, int note_id) throws IOException;
    List<Files> getAllFilesByNoteId(int note_id);
}
