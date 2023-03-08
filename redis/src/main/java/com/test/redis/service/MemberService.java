package com.test.redis.service;

import com.test.redis.entity.Member;
import com.test.redis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void insertRedis() {
        for (int i = 1; i < 10000; i++) {
            Member member = Member.builder()
                    .name("이름["+i+"]")
                    .age("나이["+i+"]")
                    .sex("성별["+i+"]")
                    .height("키["+i+"]")
                    .weight("체중["+i+"]")
                    .build();
            log.info("몇번째 저장 : {}",i);
            memberRepository.save(member);
        }
        log.info("저장 완료...");
    }

    @Cacheable("member")
    public List<Member> selectRedis() {
       return memberRepository.findAll();
    }
}
