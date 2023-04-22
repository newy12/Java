package com.jpa.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        //JPA N+1
        SpringApplication.run(JpaApplication.class, args);
    }

}
