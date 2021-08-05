package com.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * 모든 엔티티들의 상위 클래스가 되어, entity들의 생성/수정시간을 자동 관리하는 역할
 */

@Getter
@MappedSuperclass // JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 클래스에 정의해둔 필드를 컬럼으로 인식하도록
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 포함
public class BaseTimeEntity {
    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createdDateTime; // 실습에서는 createdDate지만 createdDateTime으로 사용

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDateTime;
}
