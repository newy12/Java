package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ChangeUserInfoResponseDto {
    private String userNickname;
    private String updateUserNickname;
    private String major1;
    private String major2;
    private String profileImageUrl;
    private String introduce;
}
