package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@Builder
public class ChangeUserInfoRequestDto {
    @Schema(description = "현재닉네임", example = "유저닉네임")
    private String userNickname;
    @Schema(description = "수정할닉네임", example = "유저닉네임")
    private String updateUserNickname;
    @Schema(description = "전공1", example = "전공1")
    private String major1;
    @Schema(description = "전공2", example = "전공2")
    private String major2;
    @Schema(description = "프로필사진첨부", example = "프로필사친첨부")
    private MultipartFile file;
    @Schema(description = "자기소개", example = "자기소개")
    private String introduce;
}
