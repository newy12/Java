package com.summar.summar.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;
}
