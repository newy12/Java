package com.summar.summar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.summar.summar.dto.FollowerSaveDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Getter
@Table(name = "FOLLOW")
@Entity
@NoArgsConstructor
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "followed_user_id")
    private User followedUser; //팔로우 당한 사람
    @Column(name = "follow_yn")
    @Type(type = "yes_no")
    private Boolean followYn; //팔로우 Y/N
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "following_user_id")
    private User followingUser; //팔로우 한사람

    public Follow(FollowerSaveDto followerSaveDto){
        this.followedUser = followerSaveDto.getFollowedUser();
        this.followYn = followerSaveDto.getFollowYn();
        this.followingUser = followerSaveDto.getFollowingUser();
    }

    public void setFollowYn(boolean followYn) {
        this.followYn = followYn;
    }
}
