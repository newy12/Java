package com.example.insertquerytestinjpa.controller;

import com.example.insertquerytestinjpa.entity.Member;
import com.example.insertquerytestinjpa.entity.Team;
import com.example.insertquerytestinjpa.repository.MemberRepository;
import com.example.insertquerytestinjpa.repository.TeamRepository;
import com.example.insertquerytestinjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1")
@RestController
@Slf4j
@RequiredArgsConstructor
public class testController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberService memberService;

    @PostMapping("/test")
    public void insert() {
        //pk 값을 알고 있다면 직접대입 하는 방법 사용.
        Long id = 1L;

        //이방법은 쿼리를 한번더 발생함으로 지양하는것이 좋다.
        //Team team = teamRepository.findById(id);
        Member member = Member.builder()
                .name("김영재")
                .teamId(id)
                .build();

        memberRepository.save(member);
    }
    @PutMapping("/test")
    public void update(){
        String name = "박영재" ;
            memberService.updateMemberInfo(name);
    }
}
