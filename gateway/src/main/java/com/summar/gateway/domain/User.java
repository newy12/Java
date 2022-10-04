package com.summar.gateway.domain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseTimeEntity implements Serializable{

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
    private String userEmail;
    private String userHpNo;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<RefreshToken> refreshTokenList = new ArrayList<>();

    @Builder
    public User(Long userSeq,String userId,String userPwd,String userName,String userNickname,String userEmail,String userHpNo){
        this.userSeq = userSeq;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userHpNo = userHpNo;
    }
}
