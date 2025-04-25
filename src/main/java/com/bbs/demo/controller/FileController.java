package com.bbs.demo.controller;

import com.bbs.demo.domain.Files;
import com.bbs.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("note_id") int note_id
    ) {
        try {
            fileService.storeFiles(files, note_id);
            return ResponseEntity.ok("파일 업로드 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        }
    }

    @GetMapping
    public ResponseEntity<List<Files>> getFiles(@RequestParam("note_id") int note_id) {
        List<Files> files = fileService.getAllFilesByNoteId(note_id);
        if (files.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(files);
    }
}
