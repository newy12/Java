package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushNotificationDto {
    private String title;
    private String body;
    private String userNickname;
    private Long seq;
}
