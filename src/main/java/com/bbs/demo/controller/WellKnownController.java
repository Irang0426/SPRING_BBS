package com.bbs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/.well-known")
public class WellKnownController {

    @GetMapping("/**")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleWellKnown() {}
}