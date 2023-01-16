package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.summar.summar.dto.FeedRegisterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Feed")
public class Feed extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedSeq;
    private String contents;
    private Long userSeq;

    @Type(type = "yes_no")
    private boolean activated;

    @Type(type = "yes_no")
    private boolean secretYn;

    @Type(type = "yes_no")
    private boolean commentYn;

    @Type(type = "yes_no")
    private boolean tempSaveYn;

    @OneToMany(mappedBy = "feed")
    @JsonIgnoreProperties(value = {"feed"}, allowSetters = true)
    private List<FeedImage> feedImages = new ArrayList<>();


    public Feed(FeedRegisterDto feedRegisterDto) {
        this.userSeq = feedRegisterDto.getUserSeq();
        this.contents = feedRegisterDto.getContents();
        this.activated = true;
        this.secretYn = feedRegisterDto.isSecretYn();
        this.commentYn = feedRegisterDto.isCommentYn();
        this.tempSaveYn = feedRegisterDto.isTempSaveYn();
    }
}
