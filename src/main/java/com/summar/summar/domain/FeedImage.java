package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "피드 이미지 시퀀스")
    private Long feedImageSeq;

    @Schema(description = "피드 시퀀스")
    private Long feedSeq;

    @Schema(description = "피드 이미지 경로")
    private String imageUrl;

    @Schema(description = "피드 이미지 순서")
    private int orderNo;

    @Schema(description = "피드 이미지 삭제 여부")
    private boolean activated;
    @ManyToOne
    @JsonIgnore
    @Schema(description = "피드")
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
