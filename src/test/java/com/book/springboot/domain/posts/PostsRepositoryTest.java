package com.book.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) //jUnit4의 @RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 @SpringBootTest를 사용하면 h2 데이터베이스를 자동으로 실행
class PostsRepositoryTest {
    // save, findAll 기능을 테스트
    @Autowired
    PostsRepository postsRepository;

    // jUnit 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    // 여러 테스트가 동시에 + 병렬적으로 수행되면 h2에 데이터가 그대로 남아있어 다른 테스트가 실패할 수 있음
    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 및 불러오기")
    public void postSaveAndLoad() {
        /**
         * given
         */
        String title = "Test Title";
        String content = "Test Content";
        String author = "Test Author";

        //테이블 posts에 insert/update 쿼리 실행
        //id가 있으면 update, 없으면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        /**
         * when
         */
        List<Posts> postsList = postsRepository.findAll();

        /**
         * then
         */
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);

    }

}