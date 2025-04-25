package com.bbs.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void storeFiles(MultipartFile[] multipartFile, int note_id) throws IOException;
}
