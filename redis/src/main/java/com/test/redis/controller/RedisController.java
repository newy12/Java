package com.test.redis.controller;

import com.test.redis.entity.Member;
import com.test.redis.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/redis")
public class RedisController {

    private final MemberService memberService;
    private final RedisTemplate<String,List<Member>> redisTemplate;

    @PostMapping("")
    public ResponseEntity<?> insertRedis(){
        memberService.insertRedis();
        return ResponseEntity.ok().build();
    }
    @GetMapping("")
    public ResponseEntity<?> selectRedis(){
        long startTime = System.currentTimeMillis();
        List<Member> result = memberService.selectRedis();
        log.info("캐시 적용 전/후 (1차) :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 전/후 (2차):" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 전/후 (3차):" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 전/후 (4차):" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 전/후 (5차):" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 전/후 (6차):" + (System.currentTimeMillis() - startTime)+ "ms");
        return ResponseEntity.ok().body(result);
    }
}
