package com.summar.summar.domain;

import com.summar.summar.dto.GatheringNotificationSaveDto;
import com.summar.summar.enumeration.NotificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Entity
@Slf4j
@NoArgsConstructor
@Table(name = "Gathering_notification")
public class GatheringNotification extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gatheringNotificationSeq;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User userSeq;

    @ManyToOne
    @JoinColumn(name = "other_user_seq")
    private User otherUserSeq;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    public GatheringNotification(GatheringNotificationSaveDto gatheringNotificationSaveDto){
        this.content = gatheringNotificationSaveDto.getContent();
        this.userSeq = gatheringNotificationSaveDto.getUserSeq();
        this.otherUserSeq = gatheringNotificationSaveDto.getOtherUserSeq();
        this.notificationType = gatheringNotificationSaveDto.getNotificationType();
    }
}
