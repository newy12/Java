package com.summar.summar.dto;




import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FollowerResponseDto{
    private String userNickname;
    private String introduce;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
}
