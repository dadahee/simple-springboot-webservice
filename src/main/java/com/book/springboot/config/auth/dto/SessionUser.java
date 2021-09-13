package com.book.springboot.config.auth.dto;

import com.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보를 저장하는 세션유저 클래스
 *
 * User 클래스 대신 SessionUser를 사용하는 이유
 *   - User 클래스에 직렬화가 구현되지 않음
 *   - but User 클래스는 엔티티임
 *   - 다른 엔티티와 관계가 생성될 경우, 직렬화 대상에 자식들까지 포함되어 성능 이슈, 부수 효과 발생 확률 높음
 *   - 따라서 직렬화 기능을 가진 세션 dto를 추가로 만들어 처리
 */

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
