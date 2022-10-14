package com.summar.summar.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RefreshTokenRequestDto {

    private UUID refreshTokenId;
}
