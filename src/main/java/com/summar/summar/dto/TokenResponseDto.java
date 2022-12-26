package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
@Builder
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
}
