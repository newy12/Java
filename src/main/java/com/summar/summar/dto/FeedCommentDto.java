package com.summar.summar.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * A DTO for the {@link com.summar.summar.domain.FeedComment} entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedCommentDto {

    private Long feedCommentSeq;

    private Long feedSeq;

    private SimpleUserVO user;

    private boolean activated;

    private List<FeedCommentDto> childComments;

    private int childCommentsCount;

    private LocalDateTime lastModifiedDate;

    private LocalDateTime createdDate;

    private String comment;

}
