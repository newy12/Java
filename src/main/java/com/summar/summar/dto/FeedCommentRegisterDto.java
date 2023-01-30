package com.summar.summar.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedCommentRegisterDto {

    private Long feedSeq;

    private Long userSeq;

    private String comment;

    private Long parentCommentSeq=0L;

}
