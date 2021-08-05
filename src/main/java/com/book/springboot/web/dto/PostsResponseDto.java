package com.book.springboot.web.dto;

import com.book.springboot.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

// restController를 사용함 -> 객체를 json 타입으로 변환 -> json 컨버터가 getter 및 기본 생성자를 필요로 하므로 추가해줘야 함
@Getter
@NoArgsConstructor
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    // entity의 필드 중 일부만 사용하므로, 모든 필드를 갖는 생성자 대신 entity를 받아서 처리
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
