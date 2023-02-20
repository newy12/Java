package com.summar.summar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PushNotificationDto {
    private String title;
    private String body;
    private String userNickname;
    private Long userSeq;  //좋아요 한사람 or 팔로우 한사람
    private Long feedCommentSeq; //댓글일때  or 대댓글일때
    private Long feedSeq; //댓글일때 or 대댓글일때
    private String pushType;

}
