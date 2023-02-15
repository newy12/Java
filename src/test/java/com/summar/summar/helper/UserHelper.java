package com.summar.summar.helper;

import com.summar.summar.domain.User;
import com.summar.summar.enumeration.SocialType;
import com.summar.summar.enumeration.UserStatus;

public class UserHelper {


    public static User createDummyUser(String userNickname) {
        try {
            return User.builder()
                    .userEmail("newy12@naver.com")
                    .userNickname(userNickname)
                    .deviceToken("deviceToken")
                    .follower(10)
                    .following(20)
                    .introduce("자기소개")
                    .major1("major1")
                    .major2("major2")
                    .profileImageUrl("image_url")
                    .pushAlarmYn(false)
                    .socialType(SocialType.APPLE)
                    .userStatus(UserStatus.normal)
                    .leaveYn(false)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
