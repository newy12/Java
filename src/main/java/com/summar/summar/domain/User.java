package com.summar.summar.domain;

import com.summar.summar.dto.SleepUserDto;
import com.summar.summar.enumeration.SocialType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Setter
@Getter
@Table(name = "USER")
@Entity
public class User extends BaseTimeEntity implements Serializable {

    /**
     * This VO is for security.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    private String userId;

    private String userPwd;

    private String userName;

    private String userNickname;

    private String userHpNo;

    private LocalDate lastLoginDate;

    @Enumerated(EnumType.STRING)
    private SocialType socialType = SocialType.APPLE;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.일반;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private RefreshToken refreshToken;

  /*  public void SleepUser(SleepUserDto sleepUserDto){
        this.userId = sleepUserDto.getUserId();
        this.userPwd = sleepUserDto.getUserPwd();
        this.userName = sleepUserDto.getUserName();
        this.userNickname = sleepUserDto.getUserNickname();
        this.userStatus = sleepUserDto.getUserStatus();
    }*/

}
