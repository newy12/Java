package com.jpa.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponseDto {
    private Long id;
    private String teamName;
}
