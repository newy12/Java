package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeUserInfoRequestDto {
    private String userNickname;
    private String major1;
    private String major2;
}
