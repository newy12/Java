package com.example.mybatismixjpa.entity;

import com.example.mybatismixjpa.dto.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickName;

    public void addMember(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public void updateMember(MemberUpdateDto memberUpdateDto) {
        this.name = memberUpdateDto.getName();
    }
}
