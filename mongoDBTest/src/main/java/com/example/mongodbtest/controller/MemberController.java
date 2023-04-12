package com.example.mongodbtest.controller;


import com.example.mongodbtest.entity.Member;
import com.example.mongodbtest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/info")
    public List<Member> selectMembers() {
        return memberRepository.findAll();
    }

    @PostMapping("/info")
    public void insertMember() {
        memberRepository.insert(Member.builder()
                .name("김영재2")
                .age("282")
                .address("서울2")
                .build());
    }
    @PutMapping("/info")
    public void updateMember(){
        Member member = memberRepository.findBy_id("64363ba99061382b9ed0f64e");
        member.setName("김영웅");
        memberRepository.save(member);
    }
    @DeleteMapping("/info")
    public void deleteMember(){
        Member member = memberRepository.findBy_id("64363ba99061382b9ed0f64e");
        memberRepository.delete(member);
    }
}
