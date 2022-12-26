package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddIntroduceRequestDto {
    @Schema(description = "닉네임", example = "유저닉네임")
    private String userEmail;
    @Schema(description = "자기소개", example = "자기소개")
    private String introduce;
}
