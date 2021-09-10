package com.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"), // 스프링 시큐리티에서는 권한 코드 앞에 ROLE_이 항상 붙어야 함!
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
