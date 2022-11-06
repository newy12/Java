package com.summar.summar.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String userEmail;
    private String userNickName;
    private String userMajor;
}
