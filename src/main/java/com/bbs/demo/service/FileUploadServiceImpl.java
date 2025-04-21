package com.bbs.demo.service;

import com.bbs.demo.domain.File;
import com.bbs.demo.mapper.FileUploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadMapper fileMapper;

    @Override
    public void storeFiles(MultipartFile[] files, int noteId) throws IOException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) continue;

            File file = new File();
            file.setNoteId(noteId);
            file.setFileName(multipartFile.getOriginalFilename());
            file.setFile(multipartFile.getBytes());

            fileMapper.insertFile(file);
        }
    }
}
