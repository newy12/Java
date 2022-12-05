package com.summar.summar.dto;

import com.summar.summar.domain.Major;
import com.summar.summar.enumeration.SocialType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSaveDto {
    private String userEmail;
    private String userNickname;
    private String major1;
    private String major2;
    private SocialType socialType;
}
