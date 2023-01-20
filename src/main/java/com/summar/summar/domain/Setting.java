package com.summar.summar.domain;


import com.summar.summar.enumeration.SettingType;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Setting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settingId;
    @Enumerated(EnumType.STRING)
    private SettingType settingType;
    private String title;
    private String content;
    private String regDatetime;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
}
