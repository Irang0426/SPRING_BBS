package com.bbs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class BoardController {

    @GetMapping("/board/register")
    public String upload_register() {
        return "upload_register";
    }

    @GetMapping("/board/list")
    public String upload_list() {
        return "upload_list";
    }

    @GetMapping("/board/read")
    public String upload_read() {
        return "upload_read";
    }

    @PostMapping("/board/upload")
    public String upload(@RequestParam("title") String title, @RequestParam("content") String content, Model model) throws IOException {

        model.addAttribute("message", "업로드 성공");
        model.addAttribute("title", title);
        model.addAttribute("content", content);

        return "upload_list";
    }

}
