package com.bbs.demo.service;

import com.bbs.demo.domain.Files;
import com.bbs.demo.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public void storeFiles(MultipartFile[] files, int note_id) throws IOException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) continue;

            Files file = new Files();
            file.setNote_id(note_id);
            file.setFilename(multipartFile.getOriginalFilename());
            file.setFiles(multipartFile.getBytes());

            // 디버깅을 위한 로그 추가
            System.out.println("업로드 파일: " + multipartFile.getOriginalFilename() + ", 크기: " + multipartFile.getSize());

            fileMapper.insertFile(file);
        }
    }

    @Override
    public List<Files> getAllFilesByNoteId(int note_id) {
        return fileMapper.findAllFilesByNoteId(note_id);
    }

    @Override
    public Files getFileById(int id) {
        return fileMapper.findFileById(id);
    }
}
