package com.summar.summar.dto;

import lombok.Data;

@Data
public class FollowerRequestDto {
    private String followedUserNickname; //팔로우신청받은사람
    private String userNickname; //팔로우 건사람
}
