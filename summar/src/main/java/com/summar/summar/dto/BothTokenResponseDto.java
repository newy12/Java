package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class BothTokenResponseDto {
    private String accessToken;
    private UUID refreshTokenSeq;

    public BothTokenResponseDto(String accessToken, UUID refreshTokenSeq){
        this.accessToken = accessToken;
        this.refreshTokenSeq = refreshTokenSeq;
    }
}
