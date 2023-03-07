package com.summar.summar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedCommentRegisterDto {

    @Schema(description = "댓글을 등록하려는 피드 시퀀스")
    private Long feedSeq;

    @Schema(description = "등록하는 유저 시퀀스")
    private Long userSeq;

    @Schema(description = "댓글 내용")
    private String comment;

    @Schema(description = "상위(부모) 댓글 시퀀스")
    private Long parentCommentSeq;

}
