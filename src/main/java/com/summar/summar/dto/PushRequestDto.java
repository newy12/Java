package com.summar.summar.dto;

import lombok.Data;

@Data
public class PushRequestDto {

    private String to;
    private String priority;
    private Notification notification;
}
