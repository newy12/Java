package com.example.mybatismixjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdateDto {
    private Long Id;
    private String name;
}
