package com.example.rabbitmq.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Producer {


    private final RabbitTemplate rabbitTemplate;

    //@Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMessage() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
}
