package com.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//ibatis나 MyBatis 등에서 DAO로 불리는 DB layer 접근자
//JPA에서는 Repository로 부르며 **인터페이스**로 생성
//JpaRepository<Entity 클래스, PK 타입>를 상속받으면 기본 CRUD 자동으로 생성
//@Repository 어노테이션도 필요 없음
//단, Entity 클래스와 Entity Repository는 함께 위치해야 함 -> Entity 클래스는 기본 Repository 없이는 제역할 불가
public interface PostsRepository  extends JpaRepository<Posts, Long> {
}
