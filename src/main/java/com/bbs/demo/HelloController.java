<<<<<<< HEAD
package com.bbs.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, 2조!";
    }
}
=======
//package com.bbs.demo;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////@RestController
//@Controller
//public class HelloController {
//
//    @GetMapping("/")
//    public String hello() {
////        return "Hello, 2조!";
//    	return "index";		// templates/index.html을 렌더링
//    }
//}
>>>>>>> 67ab94ea125d9f2b175fb8c924754bbaf386a4d6
