package com.bbs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/board/register")
    public String board_register() {
        return "board_register";
    }

    @GetMapping("/board/list")
    public String board_list() {
        return "board_list";
    }

    @GetMapping("/board/read")
    public String board_read() {
        return "board_read";
    }

    @PostMapping("/board/upload")
    public String board_upload(@RequestParam("title") String title, @RequestParam("content") String content, Model model) throws IOException {

        model.addAttribute("message", "업로드 성공");
        model.addAttribute("title", title);
        model.addAttribute("content", content);

        return "board_list";
    }

}
