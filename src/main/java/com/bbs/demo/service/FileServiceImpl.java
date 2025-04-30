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
    public void storeFiles(MultipartFile[] files, Integer note_id) throws IOException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) continue;

            Files file = new Files();
            file.setNote_id(note_id);
            file.setFilename(multipartFile.getOriginalFilename());
            file.setFiles(multipartFile.getBytes());

            fileMapper.insertFile(file);
        }
    }

    @Override
    public List<Files> getAllFilesByNoteId(Integer note_id) {
        return fileMapper.findAllFilesByNoteId(note_id);
    }

    @Override
    public Files getFileById(Integer id) {
        return fileMapper.findFileById(id);
    }

    @Override
    public void deleteFileById(Integer id) {
        fileMapper.deleteFileById(id);
    }

    @Override
    public void removeDeletedFiles(int noteId, List<Integer> remainingFileIds) {
        List<Files> allFiles = fileMapper.findAllFilesByNoteId(noteId);

        for (Files file : allFiles) {
            // remainingFileIds가 null이거나 해당 파일이 목록에 없으면 삭제
            if (remainingFileIds == null || !remainingFileIds.contains(file.getId())) {
                fileMapper.deleteFileById(file.getId());
            }
        }
    }
}
