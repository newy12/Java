package com.summar.summar.dto;

import com.summar.summar.enumeration.SocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginCheckRequestDto {
    @Schema(description = "소셜 로그인 시, 이메일 부분", example = "newy12@naver.com")
    private String userEmail;

    @Schema(description = "소셜 로그인 종류", example = "GOOGLE")
    private SocialType socialType;

}
