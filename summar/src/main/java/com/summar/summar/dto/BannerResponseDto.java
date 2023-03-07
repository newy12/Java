package com.summar.summar.dto;

import com.summar.summar.domain.Banner;
import lombok.Data;

@Data
public class BannerResponseDto {
    private String imageUrl;
    public BannerResponseDto(Banner banner){
        this.imageUrl = banner.getImageUrl();
    }
}
