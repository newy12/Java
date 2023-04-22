package com.example.insertquerytestinjpa.repository;

import com.example.insertquerytestinjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
