package com.summar.summar.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "BANNER")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bannerSeq;
    private String imageUrl;
}
