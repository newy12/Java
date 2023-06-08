package com.example.mybatismixjpa.controller;

import com.example.mybatismixjpa.dto.MemberUpdateDto;
import com.example.mybatismixjpa.entity.Member;
import com.example.mybatismixjpa.mapper.MemberMapper;
import com.example.mybatismixjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberMapper mapper;

    //jpa - insert
    @PostMapping("/test")
    public void addMemberJPA(){
        Member member = new Member();
        member.addMember("김영재","닉네임");
        memberRepository.save(member);
    }
    //jpa - select
    @GetMapping("/test")
    public List<Member> selectMemberJPA(){
        return memberRepository.findAll();
    }
    //jpa - update
    @PutMapping("/test")
    @Transactional
    public void updateMemberJPA(){
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto(3L,"수정자김영재");
        Member member = memberRepository.findById(memberUpdateDto.getId()).get();
        member.updateMember(memberUpdateDto);
    }
    //jpa - delete
    @DeleteMapping("/test")
    public void deleteMemberJPA(){
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto(3L,"수정자김영재");
        Member member = memberRepository.findById(memberUpdateDto.getId()).get();
        memberRepository.delete(member);
    }


    //mybatis - select
    @GetMapping("/test2")
    public List<Member> selectMemberMybatis(){
        List<Member> results = mapper.findById();
        log.info("results : {}" , results);
        return results;
    }
    //mybatis - insert
    @PostMapping("/test2")
    public void addMemberMybatis(){
        Member member = new Member();
        member.addMember("김영재-마이바티스","닉네임");
        mapper.addMember(member);
    }
    //mybatis - update
    @PutMapping("/test2")
    public void updateMemberMybatis(){
        MemberUpdateDto memberUpdateDto = new MemberUpdateDto(3L,"수정자김영재");

        mapper.updateMember(memberUpdateDto);
    }
    //mybatis - delete;
    @DeleteMapping("/test2")
    public void deleteMemberMybatis(){
        mapper.deleteMember(1L);
    }
}
