package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.Follow;
import com.summar.summar.domain.User;
import com.summar.summar.dto.*;
import com.summar.summar.repository.FollowRepository;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PushService pushService;


    @Transactional(readOnly = true)
    public Page<FollowerResponseDto> findFollowers(String userNickname, Pageable pageable) {
        //해당 유저의 팔로워들 정보 추출
        User user = userRepository.findByUserNickname(userNickname).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Page<Follow> followerList = followRepository.findByFollowedUserNicknameAndFollowYn(user.getUserNickname(), true, pageable);
        return followerList.map(m -> FollowerResponseDto.builder()
                .userNickname(m.getUser().getUserNickname())
                .introduce(m.getUser().getIntroduce())
                .follower(m.getUser().getFollower())
                .following(m.getUser().getFollowing())
                .major1(m.getUser().getMajor1())
                .major2(m.getUser().getMajor2())
                .build());
    }

  /*  @Transactional(readOnly = true)
    public Page<?> findFollowings(String userNickname, Pageable pageable) {
        User user = userRepository.findByUserNickname(userNickname).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Page<Follow> followingList = followRepository.findByUserAndFollowYn(user, true, pageable);
        followingList.map(m -> {
            User userInfo = userRepository.findByUserNickname(m.getFollowedUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));

        })
    }*/

    public void addFollower(FollowerRequestDto followerRequestDto) {
        if (followerRequestDto.getFollowedUserNickname().equals(followerRequestDto.getUserNickname())) {
            throw new NotFoundException("같을수없다");
        }
        User followedUser = userRepository.findByUserNickname(followerRequestDto.getFollowedUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        User user = userRepository.findByUserNickname(followerRequestDto.getUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Follow followInfo = followRepository.findByFollowedUserNicknameAndUserAndFollowYn(followerRequestDto.getFollowedUserNickname(), user, false).orElse(null);
        //푸쉬 알람
        PushNotificationDto pushNotificationDto = PushNotificationDto.builder()
                .title("Summar")
                .body(followerRequestDto.getUserNickname() + "님이 회원님을 팔로우했어요.")
                .userNickname(followerRequestDto.getFollowedUserNickname())
                .build();
        //팔로우 정보가 없다면
        if (followInfo == null) {
            FollowerSaveDto followerSaveDto = FollowerSaveDto.builder()
                    .followedUserNickname(followedUser.getUserNickname())
                    .followYn(true)
                    .user(user)
                    .build();
            followRepository.save(new Follow(followerSaveDto));
            Integer followerCount = followRepository.countByFollowedUserNicknameAndFollowYn(followedUser.getUserNickname(), true);
            Integer followingCount = followRepository.countByUserAndFollowYn(user, true);
            followedUser.updateFollower(followerCount);
            user.updateFollowing(followingCount);
            userRepository.save(user);
            userRepository.save(followedUser);
        } else {
            //기존 팔로우 정보가 있다면
            followInfo.setFollowYn(true);
            followRepository.save(followInfo);
            Integer followerCount = followRepository.countByFollowedUserNicknameAndFollowYn(followedUser.getUserNickname(), true);
            Integer followingCount = followRepository.countByUserAndFollowYn(user, true);
            followedUser.updateFollower(followerCount);
            user.updateFollowing(followingCount);
            userRepository.save(followedUser);
        }
        //푸시알림 발송
        pushService.pushNotification(pushNotificationDto);
    }

    @Transactional
    public void deleteFollower(FollowerRequestDto followerRequestDto) {
        User userInfo = userRepository.findByUserNickname(followerRequestDto.getUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        User followedUser = userRepository.findByUserNickname(followerRequestDto.getFollowedUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Follow followInfo = followRepository.findByFollowedUserNicknameAndUserAndFollowYn(followerRequestDto.getFollowedUserNickname(), userInfo, true).orElseThrow(() -> new SummarCommonException(SummarErrorCode.FOLLOW_NOT_FOUND.getCode(), SummarErrorCode.FOLLOW_NOT_FOUND.getMessage()));
        followInfo.setFollowYn(false);
        followRepository.save(followInfo);
        Integer followerCount = followRepository.countByFollowedUserNicknameAndFollowYn(followedUser.getUserNickname(), true);
        Integer followingCount = followRepository.countByUserAndFollowYn(userInfo, true);
        followedUser.updateFollower(followerCount);
        userInfo.updateFollowing(followingCount);
        userRepository.save(userInfo);
        userRepository.save(followedUser);
    }
}
