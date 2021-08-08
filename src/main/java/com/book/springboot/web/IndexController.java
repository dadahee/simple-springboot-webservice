package com.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // 머스테치 스타터 덕분에
        // 컨트롤러에서 문자열을 반환할 때
        // 앞의 디렉토리 경로와 뒤의 파일 확장자는 자동으로 지정
        return "index"; // -> index.mustache를 view resolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }


}
