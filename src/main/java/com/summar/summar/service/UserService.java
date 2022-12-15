package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.User;
import com.summar.summar.dto.LoginRequestDto;
import com.summar.summar.dto.UserSaveDto;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User saveUser(LoginRequestDto loginRequestDto) {
        return userRepository.save(
                new User(UserSaveDto.builder()
                        .userEmail(loginRequestDto.getUserEmail())
                        .userNickname(loginRequestDto.getUserNickname())
                        .major1(loginRequestDto.getMajor1())
                        .major2(loginRequestDto.getMajor2())
                        .follower(0)
                        .following(0)
                        .socialType(loginRequestDto.getSocialType())
                        .lastLoginDate(LocalDate.now())
                .build())
        );
    }

    @Transactional(readOnly = true)
    public Boolean checkNicknameDuplication(String nickname) throws NoSuchAlgorithmException {
        return userRepository.existsByUserNickname(nickname);
    }

    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userRepository.findByUserEmail(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));
    }

    @Transactional
    public void updateLastUserLoginDate(User userInfo) {
        userInfo.setLastLoginDate(LocalDate.now());
        userRepository.save(userInfo);
    }
    @Transactional(readOnly = true)
    public boolean checkUserEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }
    @Transactional(readOnly = true)
    public User findUserInfo(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
    }


}
