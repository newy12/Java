package com.summar.summar.domain;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Setting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;
    private String profileEdit; //프로필편집
    private String pushNotification; //푸시 알림
    private String notice; //공지사항
    private String question; //자주묻는질문
}
