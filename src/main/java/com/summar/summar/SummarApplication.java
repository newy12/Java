package com.summar.summar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableCaching //캐시 사용 활성화
@EnableScheduling //휴면계정 스케쥴러 활성화
@SpringBootApplication
public class SummarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummarApplication.class, args);
    }

}
