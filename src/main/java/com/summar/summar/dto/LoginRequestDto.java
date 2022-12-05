package com.summar.summar.dto;

import com.summar.summar.enumeration.SocialType;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String userEmail;
    private String userNickName;
    private String major1;
    private String major2;
    private SocialType socialType;
}
