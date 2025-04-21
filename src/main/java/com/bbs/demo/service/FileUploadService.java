package com.bbs.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    void storeFiles(MultipartFile[] multipartFile, int noteId) throws IOException;
}
