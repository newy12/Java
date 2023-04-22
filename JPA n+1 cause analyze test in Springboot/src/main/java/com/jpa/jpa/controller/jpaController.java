package com.jpa.jpa.controller;

import com.jpa.jpa.entity.Member;
import com.jpa.jpa.entity.Team;
import com.jpa.jpa.repository.MemberRepository;
import com.jpa.jpa.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/jpa")
@RequiredArgsConstructor
public class jpaController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @PostMapping("")
    public ResponseEntity<?> insert(){

        Team team = Team.builder()
                .teamName("청팀")
                .build();

        teamRepository.save(team);

        Member member = Member.builder()
                .name("김영재")
                .team(team)
                .build();

        memberRepository.save(member);

        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public List<Member> select(){
        List<Member> members1 = memberRepository.findAll();
        for (Member member:members1){
            System.out.println("member = " + member.getTeam().getTeamName());
        }
        return members1;
/*       return members2.stream().map(member ->
                MemberResponseDto.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .teamInfo(TeamResponseDto.builder()
                                .id(member.getTeam().getId())
                                .teamName(member.getTeam().getTeamName())
                                .build())
                        .build());*/
    }
}
