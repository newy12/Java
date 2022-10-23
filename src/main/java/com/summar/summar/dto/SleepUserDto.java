package com.summar.summar.dto;

import com.summar.summar.domain.User;
import com.summar.summar.domain.UserStatus;
import com.summar.summar.enumeration.SocialType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class SleepUserDto {
    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userHpNo;
    private LocalDate lastLoginDate;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    public SleepUserDto(User entity){
        this.userId = entity.getUserId();
        this.userPwd = entity.getUserPwd();
        this.userName = entity.getUserName();
        this.userNickname = entity.getUserNickname();
        this.userHpNo = entity.getUserHpNo();
        this.lastLoginDate = entity.getLastLoginDate();
        this.userStatus = entity.getUserStatus();
        this.socialType = entity.getSocialType();
    }
}
