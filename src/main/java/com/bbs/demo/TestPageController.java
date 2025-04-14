package com.bbs.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {
	
	@GetMapping("/test01")
	public String test01() {
		return "test01";
	}

	@GetMapping("/test02")
	public String test02() {
		return "test02";
	}

	@GetMapping("/test03")
	public String test03() {
		return "test03";
	}

	@GetMapping("/test04")
	public String test04() {
		return "test04";
	}

	@GetMapping("/test05")
	public String test05() {
		return "test05";
	}

	@GetMapping("/test06")
	public String test06() {
		return "test06";
	}
}
