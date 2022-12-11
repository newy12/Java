package com.summar.summar.domain;

import com.summar.summar.dto.LoginRequestDto;
import com.summar.summar.dto.UserSaveDto;
import com.summar.summar.enumeration.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseTimeEntity implements Serializable {

    /**
     * This VO is for security.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;
    private String userNickname;
    private String userEmail;
    private LocalDate lastLoginDate;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.normal;
    private String major1; //계열
    private String major2; //전공

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private RefreshToken refreshToken;

    @Builder
    public User(Long userSeq, String userEmail, String userNickname) {
        this.userSeq = userSeq;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
    }

    public User(LoginRequestDto loginRequestDto) {
        this.userNickname = loginRequestDto.getUserNickName();
        this.userEmail = loginRequestDto.getUserEmail();
    }

    public User(UserSaveDto userSaveDto){
        this.userEmail = userSaveDto.getUserEmail();
        this.userNickname = userSaveDto.getUserNickname();
        this.major1 = userSaveDto.getMajor1();
        this.major2 = userSaveDto.getMajor2();
        this.socialType = userSaveDto.getSocialType();
        this.lastLoginDate = userSaveDto.getLastLoginDate();
    }
    public void setLastLoginDate(LocalDate lastLoginDate){
        this.lastLoginDate = lastLoginDate;
    }
    public void setUserStatus(UserStatus userStatus){
        this.userStatus = userStatus;
    }
}
