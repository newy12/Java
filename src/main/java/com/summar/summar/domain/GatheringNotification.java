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

    @ManyToOne
    @JoinColumn(name = "feed_seq")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "feed_comment_seq")
    private FeedComment feedComment;

    private String imageUrl;

    private String feedImageUrl;

    //팔로우 체크
    private Boolean followCheck = false;

    public void setFollowCheck(Boolean followCheck){
        this.followCheck = followCheck;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public FeedComment getFeedComment() {
        return feedComment;
    }

    public void setFeedComment(FeedComment feedComment) {
        this.feedComment = feedComment;
    }

    public GatheringNotification(GatheringNotificationSaveDto gatheringNotificationSaveDto){
        this.content = gatheringNotificationSaveDto.getContent();
        this.userSeq = gatheringNotificationSaveDto.getUserSeq();
        this.otherUserSeq = gatheringNotificationSaveDto.getOtherUserSeq();
        if(gatheringNotificationSaveDto.getFeed()!=null){
            this.feed = gatheringNotificationSaveDto.getFeed();
        }
        if(gatheringNotificationSaveDto.getFeedComment()!=null){
            this.feedComment = gatheringNotificationSaveDto.getFeedComment();
        }
        this.imageUrl = gatheringNotificationSaveDto.getImageUrl();
        this.feedImageUrl = gatheringNotificationSaveDto.getFeedImageUrl();
        this.notificationType = gatheringNotificationSaveDto.getNotificationType();
    }
}
