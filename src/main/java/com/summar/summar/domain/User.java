package com.summar.summar.domain;

import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.enumeration.SocialType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
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

    @Builder
    public User(Long userSeq,String userId,String userPwd,String userName,String userNickname,String userHpNo){
        this.userSeq = userSeq;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userHpNo = userHpNo;
    }

    public User(JoinRequestDto joinRequestDto) {
        this.userId = joinRequestDto.getUserId();
        this.userName = joinRequestDto.getUserName();
        this.userNickname = joinRequestDto.getUserNickname();
        this.userHpNo = joinRequestDto.getUserHpNo();
        this.userPwd = joinRequestDto.getUserPwd();
    }
}
