package com.book.springboot.config.auth;

import com.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable() // h2-console 화면 사용을 위해 disable 처리
            .and()
                .authorizeRequests() // url별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**", // authorizeRequests()가 선언되어야 사용할 수 있다.
                        "/js/**", "/h2-console/**").permitAll() // 권한 관리 대상을 지정, URL/HTTP 메서드 별로 관리 가능, permitAll을 통해 전체 열람 가능
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // POST 요청이면서 /api/v1/** 하위 API는 USER 권한을 가진 사람만 가능
                .anyRequest().authenticated() // 설정값 이외의 나머지 URL, authenticated(): 인증된(로그인한) 사용자만 접근 가
            .and()
                    .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
                    .logoutSuccessUrl("/") // 로그아웃 성공 시 / 주소로 이동
            .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 설정 진입점
                    .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                        .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 구현체 등록
    }
}
