package com.bbs.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UploadController {

    @GetMapping("/upload/register")
    public String upload_register() {
        return "upload_register";
    }

    @GetMapping("/upload/list")
    public String upload_list() {
        return "upload_list";
    }

    @GetMapping("/upload/read")
    public String upload_read() {
        return "upload_read";
    }

    @PostMapping("/upload")
    public String upload() {
        return "upload_list";
    }

}
