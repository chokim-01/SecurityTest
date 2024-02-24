package com.example.jg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Entity 수정,생성 날짜 또는 생성/수정자 자동 추가 / abstract class BaseTimeEntity
@SpringBootApplication
public class JgApplication {
    // Main class
    public static void main(String[] args) {
        SpringApplication.run(JgApplication.class, args);
    }

}
