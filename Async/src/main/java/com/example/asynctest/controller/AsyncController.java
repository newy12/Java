package com.example.asynctest.controller;

import com.example.asynctest.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AsyncController {
    private final AsyncService asyncService;

    @GetMapping("/test")
    private String test(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            asyncService.createDummy(i);
        }
        log.info("성능 시간 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("성능 시간 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("성능 시간 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("성능 시간 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("성능 시간 :" + (System.currentTimeMillis() - startTime)+ "ms");
        return "h2";
    }
}
