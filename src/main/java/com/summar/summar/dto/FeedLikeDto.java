package com.summar.summar.dto;

import lombok.*;


/**
 * A DTO for the {@link com.summar.summar.domain.FeedLike} entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedLikeDto {

    private Long feedSeq;

    private Long userSeq;

    private boolean activated;

}
