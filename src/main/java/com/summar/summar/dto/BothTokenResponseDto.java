package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BothTokenResponseDto {
    private String accessToken;
    private UUID refreshTokenSeq;
}
