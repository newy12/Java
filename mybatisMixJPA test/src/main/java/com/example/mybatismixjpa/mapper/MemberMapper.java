package com.example.mybatismixjpa.mapper;

import com.example.mybatismixjpa.dto.MemberUpdateDto;
import com.example.mybatismixjpa.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MemberMapper {

   List<Member> findById();

   void addMember(Member member);

   void updateMember(MemberUpdateDto memberUpdateDto);

   void deleteMember(Long id);
}
