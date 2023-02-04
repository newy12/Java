package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedCommentUpdateDto {

    @Schema(description = "피드 댓글 시퀀스")
    private Long feedCommentSeq;

    @Schema(description = "업데이트할 댓글 내용")
    private String comment;

}
