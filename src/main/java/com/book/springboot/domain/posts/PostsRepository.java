package com.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//ibatis나 MyBatis 등에서 DAO로 불리는 DB layer 접근자
//JPA에서는 Repository로 부르며 **인터페이스**로 생성
//JpaRepository<Entity 클래스, PK 타입>를 상속받으면 기본 CRUD 자동으로 생성
//@Repository 어노테이션도 필요 없음
//단, Entity 클래스와 Entity Repository는 함께 위치해야 함 -> Entity 클래스는 기본 Repository 없이는 제역할 불가
public interface PostsRepository  extends JpaRepository<Posts, Long> {

    // spring data jpa에서 제공하지 않는 코드는 쿼리로 작성해도 됨
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    /**
     * 규모가 있는 프로젝트에서의 데이터 조회는 복잡한 조건으로 인해 Entity 클래스만으로 처리하기 어려움
     * -> 따라서 조회를 위한 프레임워크(ex. QueryDsl, jooq, MyBatis 등)를 추가로 사용
     * 3가지 프레임워크 중 하나를 조회 후 등록/수정/삭제는 SpringDataJPA를 통해 진행.
     *
     * 저자님은 Querydsl를 추천하시는데, 이유는
     * 1. 타입 안정성 보장
     *    메소드 기반의 쿼리 생성으로, 오타나 존재하지 않는 컬럼명을 명시하면 ide에서 자동 검출됨
     *    jooq 지원, mybatis 지원 x
     * 2. 국내 많은 회사에서 사용
     * 3. 레퍼런스가 많음
     */
}
