package com.summar.summar.dto;

import com.summar.summar.domain.Major;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String userEmail;
    private String userNickName;
    private Major major;
}
