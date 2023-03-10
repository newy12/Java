package com.test.redis.controller;

import com.test.redis.entity.Member;
import com.test.redis.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
        log.info("캐시 적용 후 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 후 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 후 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 후 :" + (System.currentTimeMillis() - startTime)+ "ms");
        log.info("캐시 적용 후 :" + (System.currentTimeMillis() - startTime)+ "ms");
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/id")
    public ResponseEntity<?> selectOneRedis(@RequestParam(value = "id")Long id){
        memberService.selectOneRedis(id);
        return ResponseEntity.ok().body(memberService.selectOneRedis(id));
    }
    @PutMapping("")
    public ResponseEntity<?> updateRedis(@RequestParam(value = "id")Long id){
        memberService.updateRedis(id);
        return ResponseEntity.ok().build();
    }
}
