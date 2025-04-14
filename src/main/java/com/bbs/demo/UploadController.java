package com.bbs.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    @GetMapping("/upload/register")
    public String register() {
        return "upload_register";
    }
}
