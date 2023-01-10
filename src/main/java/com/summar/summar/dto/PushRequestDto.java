package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushRequestDto {

    private String to;
    private String priority;
    private Notification notification;
}
