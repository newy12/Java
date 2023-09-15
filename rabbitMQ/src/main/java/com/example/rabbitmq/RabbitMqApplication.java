package com.example.rabbitmq;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public abstract class RabbitMqApplication {


    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);

    }

    public abstract void run(ApplicationArguments args) throws Exception;
}
