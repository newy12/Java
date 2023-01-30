package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.summar.summar.dto.FeedCommentRegisterDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Feed_Comment")
public class FeedComment extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedCommentSeq;

    @ManyToOne
    @JoinColumn(name="feed_seq", referencedColumnName = "feed_seq")
    @JsonIgnoreProperties(value = { "feedComments" }, allowSetters = true)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name="user_seq", referencedColumnName = "user_seq")
    @JsonIgnoreProperties(value = { "feedComments" }, allowSetters = true)
    private User user;

    private Long parentCommentSeq;

    private String comment;

    @Type(type = "yes_no")
    private boolean activated;

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Builder
    public FeedComment(FeedCommentRegisterDto feedCommentRegisterDto, Feed feed, User user) {
        this.feed = feed;
        this.user = user;
        this.activated = true;
        this.parentCommentSeq = feedCommentRegisterDto.getParentCommentSeq();
        this.comment = feedCommentRegisterDto.getComment();
    }
}
