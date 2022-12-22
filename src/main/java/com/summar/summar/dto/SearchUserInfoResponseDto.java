package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchUserInfoResponseDto {
    private String userNickname;
    private Integer follower;
    private Integer following;
    private String userEmail;
    private String major1;
    private String major2;
}
