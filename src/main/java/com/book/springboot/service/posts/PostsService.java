package com.book.springboot.service.posts;

import com.book.springboot.domain.posts.PostsRepository;
import com.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/** 스프링에서 빈을 주입받는 방법
 *  1. 생성자: 추천
 *  2. setter
 *  3. @Autowired: 비추
 */
// 롬복 어노테이션 사용의 장점: 클래스 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수정할 필요가 없다.
@RequiredArgsConstructor //생성자 주입 지원: final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해줌
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
