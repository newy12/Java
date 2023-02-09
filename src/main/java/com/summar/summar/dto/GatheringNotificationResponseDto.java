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
    //알림타입
    private NotificationType notificationType;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    public GatheringNotificationResponseDto(GatheringNotification gatheringNotification){
        this.content = gatheringNotification.getContent();
        this.userSeq = gatheringNotification.getUserSeq().getUserSeq();
        this.otherUserSeq = gatheringNotification.getOtherUserSeq().getUserSeq();
        this.notificationType = gatheringNotification.getNotificationType();
        this.createdDate = gatheringNotification.getCreatedDate();
    }
}
