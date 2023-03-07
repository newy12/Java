package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notification {
    private String title;
    private String sound;
    private String body;
}
