package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchUserListResponseDto {
    private String userNickname;
}
