package com.example.jg.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 공통 매핑 정보가 필요할 때 사용 여기서는 선언만 하고 상속받아서 사용
@EntityListeners(AuditingEntityListener.class) // Spring Data JPA의 이벤트 리스너. 영속,수정 이벤트 감지
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
