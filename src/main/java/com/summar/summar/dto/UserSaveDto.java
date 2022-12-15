package com.summar.summar.dto;

import com.summar.summar.enumeration.SocialType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserSaveDto {
    private String userEmail;
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
    private SocialType socialType;
    private LocalDate lastLoginDate;
}
