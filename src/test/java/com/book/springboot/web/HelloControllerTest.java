package com.book.springboot.web;
import com.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// jUnit4
// @RunWith(SpringRunner.class)
// JUnit에 내장된 실행자 외에 다른 실행자 실행
// 스프링 부트 테스트와 JUnit 사이의 연결자 역할

// jUnit5
@ExtendWith(SpringExtension.class)
//@WebMvcTest // Web(Spring MVC)에 집중할 수 있는 어노테이션
            // WebMvcConfigurer, WebMvcConfigureerAdapter를 비롯한 @Controller, @ControllerAdvice 등 사용 가능
            // @Service, @Component, @Repository 사용 불가 -> CustomOAuth2UserService 스캔 안 함
            // SecurityConfig까진 읽지만 이를 위해 필요한 CustomOAuth2UserService를 스캔하지 않아 테스트 문제 발생
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
            @ComponentScan.Filter(
                    type = FilterType.ASSIGNABLE_TYPE,
                    classes = SecurityConfig.class // 스캔 대상에서 SecurityConfig 삭제
            )
        }
)
class HelloControllerTest {

    // 스프링 빈이 관리하는 빈을 주입 받기
    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 http get, post 등에 대한 api 테스트 가능

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello wnthfh get 요청, 체이닝 지원
                .andExpect(status().isOk()) // HTTP header의 status 검증 - 200, isOk 인지 아닌지 확인
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // param(): api 테스트할 때 사용될 요청 파라미터 설정, 값은 String만 허용
                        .param("amount", String.valueOf(amount))) // int -> String으로 변환
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath: json 응답값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount))); // $를 기준으로 필드명 명시
    }
}