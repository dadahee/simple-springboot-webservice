package com.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

///////// 패키지 및 클래스 설명 /////////
// domain: 도메인을 담을 패키지
// 도메인: 소프트웨어에 대한 요구사항, 문제 영역
// xml에 쿼리를 담고, 클래스는 오직 쿼리의 결과만 담던 일들이 이제는 모두 도메인 클래스에서 해결

//어노테이션 순서: 주요 어노테이션일수록 클래스에 가깝게
@Getter // 롬복 어노테이션
@NoArgsConstructor // 롬복 어노테이션
@Entity // JPA 어노테이션
public class Posts { // 실제 데이터베이스의 테이블과 매칭될 클래스, Entity 클래스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
