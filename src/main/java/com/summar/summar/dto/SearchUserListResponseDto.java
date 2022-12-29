package com.summar.summar.dto;

import com.summar.summar.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class SearchUserListResponseDto {
    private String userNickname;
    private String major1;
    private String major2;
    private Integer follower;
    private Integer following;
    private String introduce;

    public SearchUserListResponseDto(User user){
        this.userNickname = user.getUserNickname();
        this.major1 = user.getMajor1();
        this.major2 = user.getMajor2();
        this.follower = user.getFollower();
        this.following = user.getFollowing();
        this.introduce = user.getIntroduce();
    }

}
