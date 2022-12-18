package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeUserInfoRequestDto {
    @Schema(description = "닉네임", example = "유저닉네임")
    private String userNickname;
    @Schema(description = "전공1", example = "전공1")
    private String major1;
    @Schema(description = "전공2", example = "전공2")
    private String major2;
}
