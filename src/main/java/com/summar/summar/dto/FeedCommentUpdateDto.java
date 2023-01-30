package com.summar.summar.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedCommentUpdateDto {

    private Long feedCommentSeq;

    private String comment;

}
