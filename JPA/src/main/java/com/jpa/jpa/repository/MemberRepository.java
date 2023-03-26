package com.jpa.jpa.repository;

import com.jpa.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m join fetch m.team t")
    List<Member> findAll();
}
