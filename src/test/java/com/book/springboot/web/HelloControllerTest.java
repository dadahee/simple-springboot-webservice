package com.book.springboot.web;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// jUnit4
// @RunWith(SpringRunner.class)
// JUnit에 내장된 실행자 외에 다른 실행자 실행
// 스프링 부트 테스트와 JUnit 사이의 연결자 역할

// jUnit5
@ExtendWith(SpringExtension.class)
@WebMvcTest // Web(Spring MVC)에 집중할 수 있는 어노테이션
            // @Controller, @ControllerAdvice 등 사용 가능
            // @Service, @Component, @Repository 사용 불가
class HelloControllerTest {

    // 스프링 빈이 관리하는 빈을 주입 받기
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 http get, post 등에 대한 api xptmxm rksmd

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello wnthfh get 요청, 체이닝 지원
                .andExpect(status().isOk()) // HTTP header의 status 검증 - 200, isOk 인지 아닌지 확인
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증
    }
}