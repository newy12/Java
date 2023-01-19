package com.summar.summar.dto;


import com.summar.summar.domain.User;
import lombok.Data;

@Data
public class SimpleUserVO {

    private Long userSeq;
    private String major1; //계열
    private String major2; //전공
    private String userNickname; //닉네임
    private String profileImageUrl; //프로필이미지경로

    public SimpleUserVO(User user){
        this.userSeq = user.getUserSeq();
        this.major1 = user.getMajor1();
        this.major2 = user.getMajor2();
        this.userNickname = user.getUserNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
