package com.summar.summar.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.summar.summar.domain.GatheringNotification;
import com.summar.summar.enumeration.NotificationType;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Data
public class GatheringNotificationResponseDto {
    //내용
    private String content;
    //내계정 seq
    private Long userSeq;
    //상대계정 seq
    private Long otherUserSeq;

    private Long feedSeq;

    private Long feedCommentSeq;

    //이미지Url
    private String imageUrl;
    //피드이미지Url
    private String feedImageUrl;
    //알림타입
    private NotificationType notificationType;
    private Boolean followCheck;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    public GatheringNotificationResponseDto(GatheringNotification gatheringNotification){
        this.userSeq = gatheringNotification.getUserSeq().getUserSeq();
        this.otherUserSeq = gatheringNotification.getOtherUserSeq().getUserSeq();
        this.notificationType = gatheringNotification.getNotificationType();
        this.content = gatheringNotification.getOtherUserSeq().getUserNickname();
        switch (gatheringNotification.getNotificationType()){
            case 댓글: content += "님이 회원님의 피드에 댓글을 달았어요.";break;
            case 좋아요: content += "님이 회원님의 피드를 좋아합니다.";break;
            case 팔로우: content += "님이 회원님을 팔로우 했어요.";break;
        }
        this.createdDate = gatheringNotification.getCreatedDate();
        this.imageUrl = gatheringNotification.getImageUrl();
        if(gatheringNotification.getFeed()!=null){
            this.feedSeq = gatheringNotification.getFeed().getFeedSeq();
        }else{
            this.feedSeq = 0L;
        }
        if(gatheringNotification.getFeedComment()!=null){
             this.feedCommentSeq = gatheringNotification.getFeedComment().getFeedCommentSeq();
        }else {
            this.feedCommentSeq = 0L;
        }
        this.feedImageUrl = gatheringNotification.getFeedImageUrl();
        this.followCheck = gatheringNotification.getFollowCheck();
    }
}
