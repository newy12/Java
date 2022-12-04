package com.summar.summar.dto;

import com.summar.summar.domain.Major;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSaveDto {
    private String userEmail;
    private String userNickname;
    private Major major;
}
