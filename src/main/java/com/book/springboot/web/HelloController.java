package com.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// JSON을 반환하는 컨트롤러
// 메소드마다 @ResponseBody를 선언하는 것을 한 번에 가능하도록 하는 어노테이션
@RestController
public class HelloController {

    // 예전 @RequestMapping(method = RequestMethod.GET) 과 동일
    // get 방식의 http request를 받아서 처리하는 api
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
