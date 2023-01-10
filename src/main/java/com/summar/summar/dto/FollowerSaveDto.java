package com.summar.summar.dto;

import com.summar.summar.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowerSaveDto {
    private String followedUserNickname;
    private Boolean followYn;
    private User user;
}
