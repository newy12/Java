package com.summar.summar.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "BANNER")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_seq")
    public Long bannerSeq;
    @Column(name = "image_url")
    private String imageUrl;
}
