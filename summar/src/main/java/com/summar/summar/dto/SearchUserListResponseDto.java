package com.summar.summar.dto;

import com.summar.summar.domain.User;
import lombok.Data;

@Data
public class SearchUserListResponseDto {

    private Long userSeq;
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
    private String introduce;
    private String profileImageUrl;

    public SearchUserListResponseDto(User user){
        this.userSeq = user.getUserSeq();
        this.userNickname = user.getUserNickname();
        this.major1 = user.getMajor1();
        this.major2 = user.getMajor2();
        this.follower = user.getFollower();
        this.following = user.getFollowing();
        this.introduce = user.getIntroduce();
        this.profileImageUrl = user.getProfileImageUrl();
    }

}
