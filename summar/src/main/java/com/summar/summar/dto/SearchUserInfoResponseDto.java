package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SearchUserInfoResponseDto {

    private Long userSeq;
    private String userNickname;
    private Integer follower;
    private Integer following;
    private String userEmail;
    private String major1;
    private String major2;

    public SearchUserInfoResponseDto(Long userSeq,String userNickname, Integer follower, Integer following, String userEmail, String major1, String major2) {
        this.userSeq = userSeq;
        this.userNickname = userNickname;
        this.follower = follower;
        this.following = following;
        this.userEmail = userEmail;
        this.major1 = major1;
        this.major2 = major2;
    }
}
