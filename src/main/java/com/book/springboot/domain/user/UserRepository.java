package com.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class UserRepository extends JpaRepository<User, Long> {
    // 소셜 로그인으로 이미 생성된 사용자 이메일인지, 처음 가입하는 사용자 이메일인지 판단
    Optional<User> findByEmail(String email);
}