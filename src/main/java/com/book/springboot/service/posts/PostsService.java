package com.book.springboot.service.posts;

import com.book.springboot.domain.posts.Posts;
import com.book.springboot.domain.posts.PostsRepository;
import com.book.springboot.web.dto.PostsListResponseDto;
import com.book.springboot.web.dto.PostsResponseDto;
import com.book.springboot.web.dto.PostsSaveRequestDto;
import com.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        //쿼리 날리는 코드가 없다.
        //JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경) 덕분
        //JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 해당 데이터는 영속성 컨텍스트가 유지된 상태
        //<더티체킹> 해당 데이터의 값을 변경하게 되면: 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영 -> Entity 값만 변경하면 별도로 Update 쿼리 필요 x
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    // readOnly 옵션: 트랜잭션 범위는 유지, 조회 기능만 남겨두어 조회 속도 개선 -> 조회만 하는 메소드에서 사용하기
    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }
}
