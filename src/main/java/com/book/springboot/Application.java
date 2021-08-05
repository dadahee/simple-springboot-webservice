package com.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA auditing 어노테이션을 모두 활성화
// 프로젝트의 메인 클래스 -> 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성을 모두 자동으로 설정
// 어노테이션 하위 경로부터 설정을 읽어가므로 프로젝트 최상단에 위치해야 함
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS(Web Application Server) 실행: 외부 WAS 필요 x, 내부 WAS 실행 o, 서버에 톰캣 설치 필요 x
        // 스프링부트에서는 내장 WAS 사용을 권장함 -> 언제 어디서나 같은 환경에서 스프링 부트 배포 가가
        // 스프링 부트로 만들어진 jar 파일(실행 가능한 java 패키징 파일)로 실행하면 됨!!
        SpringApplication.run(Application.class, args);
    }
}

