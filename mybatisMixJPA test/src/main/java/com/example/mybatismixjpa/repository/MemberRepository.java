package com.example.mybatismixjpa.repository;


import com.example.mybatismixjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
