package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "피드 댓글 시퀀스")
    private Long feedCommentSeq;

    @Schema(description = "피드 시퀀스")
    private Long feedSeq;

    @Schema(description = "유저 정보")
    private SimpleUserVO user;

    @Schema(description = "삭제 여부")
    private boolean activated;

    @Schema(description = "상위(부모) 댓글 시퀀스")
    private Long parentCommentSeq;

    @Schema(description = "마지막 수정 날짜")
    private LocalDateTime lastModifiedDate;

    @Schema(description = "최초 등록 날짜")
    private LocalDateTime createdDate;

    @Schema(description = "댓글 내용")
    private String comment;

}
