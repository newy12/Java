package com.summar.summar.dto;

import lombok.Data;


import java.util.List;

@Data
public class FollowingResponseDto {
    private List<FollowerResponseDto> followerResponseDtos;
}
