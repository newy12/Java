package com.summar.summar.dto;

import com.summar.summar.enumeration.SocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequestDto {
    @Schema(description = "소셜 로그인 시, 이메일 부분", example = "newy12@naver.com")
    private String userEmail;
    @Schema(description = "닉네임", example = "레몬나르고빛갚으리오")
    private String userNickname;
    @Schema(description = "전공계열 택", example = "공학계열")
    private String major1;
    @Schema(description = "전공 택", example = "컴퓨터정보공학과")
    private String major2;
    @Schema(description = "소셜 로그인 종류", example = "KAKAO")
    private SocialType socialType;
}
