package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


/**
 * A DTO for the {@link com.summar.summar.domain.FeedLike} entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedLikeDto {
    
    @Schema(description = "좋아요 추가/삭제 할 피드 시퀀스")
    private Long feedSeq;

    @Schema(description = "좋아요 추가/삭제 하는 유저 시퀀스")
    private Long userSeq;

}
