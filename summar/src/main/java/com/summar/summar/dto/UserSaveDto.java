package com.summar.summar.dto;

import com.summar.summar.enumeration.SocialType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserSaveDto {
    private String userEmail;
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
    private SocialType socialType;
    private LocalDate lastLoginDate;
    private String deviceToken;
    private Boolean pushAlarmYn;
    private Boolean leaveYn;

    public UserSaveDto(String userEmail, String userNickname, String major1, String major2, Integer follower, Integer following, SocialType socialType, LocalDate lastLoginDate, String deviceToken, Boolean pushAlarmYn,Boolean leaveYn){
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.major1 = major1;
        this.major2 = major2;
        this.follower = follower;
        this.following = following;
        this.socialType = socialType;
        this.lastLoginDate = lastLoginDate;
        this.deviceToken = deviceToken;
        this.pushAlarmYn = pushAlarmYn;
        this.leaveYn = leaveYn;
    }
}
