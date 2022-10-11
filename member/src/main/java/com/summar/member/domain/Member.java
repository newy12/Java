package com.summar.member.domain;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
public class Member extends BaseTimeEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String nickname;
    private String profileImageUrl;
    private String profileMessage;
    @Type(type = "yes_no")
    private boolean active;
    private String job_category;
}
