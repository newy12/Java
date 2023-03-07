package com.summar.summar.dto;




import com.summar.summar.enumeration.FollowStatus;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class FollowerResponseDto{
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
    private String profileImageUrl;
    private Long userSeq;
    private Boolean followUp;
    private FollowStatus followStatus;
}

