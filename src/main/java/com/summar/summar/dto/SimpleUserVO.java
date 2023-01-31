package com.summar.summar.dto;


import com.summar.summar.domain.User;
import lombok.Data;

@Data
public class SimpleUserVO {

    private Long userSeq;
    private String userEmail;
    private String major1; //계열
    private String major2; //전공
    private String userNickname; //닉네임
    private String profileImageUrl; //프로필이미지경로
    private boolean leaveYn;
    private int follower;
    private int following;


    public SimpleUserVO(User user){
        this.userSeq = user.getUserSeq();
        this.userEmail = user.getUserEmail();
        this.major1 = user.getMajor1();
        this.major2 = user.getMajor2();
        this.userNickname = user.getUserNickname();
        this.profileImageUrl = user.getProfileImageUrl();
        this.leaveYn = user.getLeaveYn();
        this.follower=user.getFollower();
        this.following=user.getFollowing();
    }
}
