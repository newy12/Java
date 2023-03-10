package com.test.redis.service;

import com.test.redis.entity.Member;
import com.test.redis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    @CacheEvict(value = "member",allEntries = true)
    public void insertRedis() {
        for (int i = 1; i < 3; i++) {
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

    @Cacheable(value = "member")
    public List<Member> selectRedis() {
       return memberRepository.findAll();
    }



    @CachePut(value = "member",key = "#id")
    public void updateRedis(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        String name = "바뀜";
        member.updateRedis(name);
        memberRepository.save(member);
    }

    @Cacheable(value = "member",key = "#id")
    public Member selectOneRedis(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}
