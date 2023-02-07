package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * A DTO for the {@link com.summar.summar.domain.FeedScrap} entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedScrapDto {

    @Schema(description = "스크랩하는 피드 시퀀스")
    private Long feedSeq;

    @Schema(description = "스크랩하는 유저 시퀀스")
    private Long userSeq;

}
