package com.summar.summar.dto;

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

    private Long feedSeq;

    private Long userSeq;

}
