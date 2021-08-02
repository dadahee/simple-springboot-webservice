package com.book.springboot.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // 테스트 검증 라이브러리(assertj)의 검증 메소드
        // 메소드 체이닝 지원
        // 검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getName()).isEqualTo(name); // isEqualTo(): assertj의 동등 비교 메소드
        assertThat(dto.getAmount()).isEqualTo(amount);

        //assertj의 장점: 왜 jUnit 대신 assertj를 사용하나?
        //CoreMatchers와 달리 추가적인 라이브러리 필요 x
        //자동완성이 확실하게 지원
    }

}