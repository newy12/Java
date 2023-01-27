package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Feed_Like")
public class FeedLike extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedLikeSeq;

    @ManyToOne
    @JoinColumn(name="feed_seq", referencedColumnName = "feed_seq")
    @JsonIgnoreProperties(value = { "feedLikes" }, allowSetters = true)
    private Feed feed;

    @OneToOne
    @JoinColumn(name="user_seq", referencedColumnName = "user_seq")
    @JsonIgnoreProperties(value = { "feedLikes" }, allowSetters = true)
    private User user;

    private boolean activated;

    public boolean isActivated() {
        return activated;
    }
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Builder
    public FeedLike(Feed feed, User user,boolean activated) {
        this.feed = feed;
        this.user = user;
        this.activated = activated;
    }
}
