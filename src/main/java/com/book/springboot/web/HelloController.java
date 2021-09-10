package com.book.springboot.web;

import com.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // @RequestParam: 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션, 어노테이션의 인자는 외부에서 넘겨준 키 이름

        // api를 호출하는 곳에서 name, amount를 넘겨주면
        // 값에 맞는 dto 객체를 생성하여 리턴
        return new HelloResponseDto(name, amount);
    }
}
