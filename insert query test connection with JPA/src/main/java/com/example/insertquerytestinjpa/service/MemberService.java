package com.example.insertquerytestinjpa.service;

import com.example.insertquerytestinjpa.entity.Member;
import com.example.insertquerytestinjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    @Transactional
    public void updateMemberInfo(String name) {
        Long id = 5L;
        Member member = memberRepository.findById(id).orElse(null);
        member.updateinfo(name);
    }
}
