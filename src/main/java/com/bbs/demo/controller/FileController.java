package com.bbs.demo.controller;

import com.bbs.demo.domain.Files;
import com.bbs.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("note_id") Integer note_id
    ) {
        try {
            fileService.storeFiles(files, note_id);
            return ResponseEntity.ok("파일 업로드 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        }
    }

    @GetMapping("/read")
    public String getFiles(@RequestParam("note_id") Integer note_Id, Model model) {
        List<Files> files = fileService.getAllFilesByNoteId(note_Id);

        model.addAttribute("files", files);
        return "file_test";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("id") Integer id) {
        Files file = fileService.getFileById(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        String originalFilename = file.getFilename();

        // RFC 5987 방식으로 UTF-8 인코딩
        String encodedFilename = URLEncoder.encode(originalFilename, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        // ISO-8859-1 인코딩 시도는 피하고 filename*= 만 사용 (중요)
        String contentDisposition = "attachment; filename*=UTF-8''" + encodedFilename;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getFiles());
    }
}
