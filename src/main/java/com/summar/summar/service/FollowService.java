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
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PushService pushService;


    @Transactional(readOnly = true)
    public Page<FollowerResponseDto> findFollowers(Long userSeq, Pageable pageable) {
        //해당 유저의 팔로워들 정보 추출
        User userInfo = userRepository.findByUserSeqAndLeaveYn(userSeq,false).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Page<Follow> followerList = followRepository.findByFollowedUserAndFollowYn(userInfo,true, pageable);
        return followerList.map(m -> FollowerResponseDto.builder()
                .userNickname(m.getFollowingUser().getUserNickname())
                .introduce(m.getFollowingUser().getIntroduce())
                .follower(m.getFollowingUser().getFollower())
                .following(m.getFollowingUser().getFollowing())
                .major1(m.getFollowingUser().getMajor1())
                .major2(m.getFollowingUser().getMajor2())
                .build());
    }

   @Transactional(readOnly = true)
    public Page<?> findFollowings(Long userSeq, Pageable pageable) {
        User userInfo = userRepository.findByUserSeqAndLeaveYn(userSeq,false).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Page<Follow> followingList = followRepository.findByFollowingUserAndFollowYn(userInfo, true, pageable);
        return followingList.map(m -> FollowerResponseDto.builder()
                .userNickname(m.getFollowedUser().getUserNickname())
                .introduce(m.getFollowedUser().getIntroduce())
                .follower(m.getFollowedUser().getFollower())
                .following(m.getFollowedUser().getFollowing())
                .major1(m.getFollowedUser().getMajor1())
                .major2(m.getFollowedUser().getMajor2())
                .build());
    }
    @Transactional
    public void addFollower(FollowerRequestDto followerRequestDto) {
        if (followerRequestDto.getFollowedUserNickname().equals(followerRequestDto.getFollowingUserNickname())) {
            throw new NotFoundException("같을수없다");
        }
        User followedUser = userRepository.findByUserNicknameAndLeaveYn(followerRequestDto.getFollowedUserNickname(),false).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        User followingUser = userRepository.findByUserNicknameAndLeaveYn(followerRequestDto.getFollowingUserNickname(),false).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Follow followInfo = followRepository.findByFollowedUserAndFollowingUserAndFollowYn(followedUser, followingUser, false).orElse(null);
        //푸쉬 알람
        PushNotificationDto pushNotificationDto = PushNotificationDto.builder()
                .title("Summar")
                .body(followerRequestDto.getFollowingUserNickname() + "님이 회원님을 팔로우했어요.")
                .userNickname(followerRequestDto.getFollowedUserNickname())
                .build();
        //팔로우 정보가 없다면
        if (followInfo == null) {
            FollowerSaveDto followerSaveDto = FollowerSaveDto.builder()
                    .followedUser(followedUser)
                    .followYn(true)
                    .followingUser(followingUser)
                    .build();
            followRepository.save(new Follow(followerSaveDto));
            Integer followerCount = followRepository.countByFollowedUserAndFollowYn(followedUser, true);
            Integer followingCount = followRepository.countByFollowingUserAndFollowYn(followingUser, true);
            followedUser.updateFollower(followerCount);
            userRepository.save(followedUser);
            followingUser.updateFollowing(followingCount);
            userRepository.save(followingUser);

        } else {
            //기존 팔로우 정보가 있다면
            followInfo.setFollowYn(true);
            followRepository.save(followInfo);
            Integer followerCount = followRepository.countByFollowedUserAndFollowYn(followedUser, true);
            Integer followingCount = followRepository.countByFollowingUserAndFollowYn(followingUser, true);
            followedUser.updateFollower(followerCount);
            userRepository.save(followedUser);
            followingUser.updateFollowing(followingCount);
            userRepository.save(followingUser);
        }
        //푸시알림 발송
        pushService.pushNotification(pushNotificationDto);
    }

    @Transactional
    public void deleteFollower(FollowerRequestDto followerRequestDto) {
        User followingUser = userRepository.findByUserNicknameAndLeaveYn(followerRequestDto.getFollowingUserNickname(),false).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        User followedUser = userRepository.findByUserNicknameAndLeaveYn(followerRequestDto.getFollowedUserNickname(),false).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        Follow followInfo = followRepository.findByFollowedUserAndFollowingUserAndFollowYn(followedUser,followingUser , true).orElseThrow(() -> new SummarCommonException(SummarErrorCode.FOLLOW_NOT_FOUND.getCode(), SummarErrorCode.FOLLOW_NOT_FOUND.getMessage()));
        followInfo.setFollowYn(false);
        followRepository.save(followInfo);
        Integer followerCount = followRepository.countByFollowedUserAndFollowYn(followedUser, true);
        Integer followingCount = followRepository.countByFollowingUserAndFollowYn(followingUser, true);
        followedUser.updateFollower(followerCount);
        followingUser.updateFollowing(followingCount);
        userRepository.save(followingUser);
        userRepository.save(followedUser);
    }
}
