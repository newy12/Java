package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FindUserInfoResponseDto {

    private Long userSeq;
    private String userNickname;
    private String major1;
    private String major2;
    private String introduce;
    private Integer follower;
    private Integer following;
    private String profileImageUrl;

    public FindUserInfoResponseDto(Long userSeq, String userNickname, String major1, String major2, String introduce, Integer follower, Integer following,String profileImageUrl){
        this.userSeq = userSeq;
        this.userNickname = userNickname;
        this.major1 = major1;
        this.major2 = major2;
        this.introduce = introduce;
        this.follower = follower;
        this.following = following;
        this.profileImageUrl = profileImageUrl;
    }
}
