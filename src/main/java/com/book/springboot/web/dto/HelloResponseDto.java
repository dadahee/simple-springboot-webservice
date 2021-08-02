package com.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// DTO: 데이터 교환을 위한 객체
@Getter // 선언된 모든 필드의 get 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성, final 없는 필드는 생성자에 포함 x
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
