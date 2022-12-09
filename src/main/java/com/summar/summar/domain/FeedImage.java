package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
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

    @ManyToOne
    @JsonIgnoreProperties(value = { "feedImages" }, allowSetters = true)
    private Feed feed;


    @Builder
    public FeedImage(Long feedSeq, String imageUrl, int orderNo) {
        this.feedSeq = feedSeq;
        this.imageUrl = imageUrl;
        this.orderNo = orderNo;
    }
}
