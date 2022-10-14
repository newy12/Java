package com.summar.summar.dto;


import com.summar.summar.domain.User;
import com.summar.summar.enumeration.SocialType;
import lombok.Data;

@Data
public class JoinRequestDto {

    private String userId;
    private String userPwd;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userHpNo;
    private SocialType socialType;

}
