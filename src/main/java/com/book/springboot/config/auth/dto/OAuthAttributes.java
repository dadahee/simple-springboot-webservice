package com.book.springboot.config.auth.dto;

import com.book.springboot.domain.user.Role;
import com.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값을 변환해주어야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        // 네이버 로그인 추가
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        // 구글 로그인
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        /* 네이버 오픈 API : 로그인 결과
        {
            "resultCode": "00",
            "message": "success",
            "response": {
                "email": "openapi@naver.com",
                "nickname": "openAPI",
                "profile_image": "~~~",
                "age": "20-29",
                "gender": "F",
                "id": "32784834",
                "name": "오픈 api",
                "birthday": "05-12"
            }
        }
         */

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profileImage"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // 처음 가입 시 아래 메서드가 호출되어 엔티티 생성
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST) // 가입 시 기본 권한
                .build();
    }
}
