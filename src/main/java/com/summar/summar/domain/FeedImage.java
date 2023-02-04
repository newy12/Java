package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Feed_Image")
public class FeedImage extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedImageSeq;

    private Long feedSeq;

    private String imageUrl;

    private int orderNo;

    private boolean activated;
    @ManyToOne
    @JsonIgnore
    private Feed feed;

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Builder
    public FeedImage(Long feedSeq, String imageUrl, int orderNo, Feed feed,boolean activated) {
        this.feedSeq = feedSeq;
        this.imageUrl = imageUrl;
        this.orderNo = orderNo;
        this.feed = feed;
        this.activated = activated;
    }
}
