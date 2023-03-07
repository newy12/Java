package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestDeleteFeedImagesDto {
    @Schema(description = "수정할 피드 시퀀스")
    private Long feedSeq;

    @Schema(description = "수정할 이미지 시퀀스 리스트")
    private List<Long> imageSeqs;
}
