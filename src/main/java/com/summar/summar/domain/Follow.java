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
    private String followedUserNickname; //팔로우 당한 사람
    @Column(name = "follow_yn",length = 1, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    private Boolean followYn; //팔로우 Y/N
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user; //팔로우 한사람

    public Follow(FollowerSaveDto followerSaveDto){
        this.followedUserNickname = followerSaveDto.getFollowedUserNickname();
        this.followYn = followerSaveDto.getFollowYn();
        this.user = followerSaveDto.getUser();
    }

    public void setFollowYn(boolean followYn) {
        this.followYn = followYn;
    }
}
