package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchUserListResponseDto {
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
    private String introduce;
}
