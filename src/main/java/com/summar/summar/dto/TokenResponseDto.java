package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
public class TokenResponseDto {

    private String accessToken;
    private UUID refreshTokenSeq;
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;

    public TokenResponseDto(String accessToken, UUID refreshTokenSeq, LoginStatus loginStatus, String userNickname, String major1, String major2, Integer follower, Integer following) {
        this.accessToken = accessToken;
        this.refreshTokenSeq = refreshTokenSeq;
        this.loginStatus = loginStatus;
        this.userNickname = userNickname;
        this.major1 = major1;
        this.major2 = major2;
        this.follower = follower;
        this.following = following;
    }
}
