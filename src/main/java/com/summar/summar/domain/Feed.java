package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.summar.summar.dto.FeedRegisterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Feed")
public class Feed extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedSeq;
    private String contents;
    private Long userSeq;
    private boolean activated;

    @OneToMany(mappedBy = "feed")
    @JsonIgnoreProperties(value = {"feed"}, allowSetters = true)
    private Set<FeedImage> feedImages = new HashSet<>();


    public Feed(FeedRegisterDto feedRegisterDto) {
        this.userSeq = feedRegisterDto.getUserSeq();
        this.contents = feedRegisterDto.getContents();
        this.activated = true;
    }
}
