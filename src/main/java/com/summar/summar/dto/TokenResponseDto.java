package com.summar.summar.dto;

import lombok.Data;

@Data
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private Boolean loginStatus;
}
