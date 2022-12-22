package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.*;
import com.summar.summar.repository.RefreshTokenRepository;
import com.summar.summar.repository.UserRepository;
import com.summar.summar.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public Boolean checkNicknameDuplication(String nickname) throws NoSuchAlgorithmException {
        return userRepository.existsByUserNickname(nickname);
    }

    @Transactional
    public void changeUserInfo(ChangeUserInfoRequestDto changeUserInfoRequestDto) {
        User user = userRepository.findByUserNickname(changeUserInfoRequestDto.getUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        user.changeUserInfo(changeUserInfoRequestDto);
        userRepository.save(user);
    }

    @Transactional
    public void addIntroduce(AddIntroduceRequestDto addIntroduceRequestDto) {
        User user = userRepository.findByUserEmail(addIntroduceRequestDto.getUserEmail()).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        user.setIntroduce(addIntroduceRequestDto.getIntroduce());
        userRepository.save(user);
    }

    @Transactional
    public String giveAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        User user = userRepository.findByUserEmail(refreshTokenRequestDto.getUserEmail()).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        if (jwtUtil.validateRefreshToken(refreshToken.getRefreshToken(), refreshTokenRequestDto.getUserEmail())) {
            String newAccessToken = jwtUtil.generateToken(refreshTokenRequestDto.getUserEmail());
            return newAccessToken;
        }
        return null;
    }

    @Transactional
    public BothTokenResponseDto giveBothToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String accessToken = jwtUtil.generateToken(refreshTokenRequestDto.getUserEmail());
        String refreshToken = jwtUtil.generateRefreshToken(refreshTokenRequestDto.getUserEmail());
        User user = userRepository.findByUserEmail(refreshTokenRequestDto.getUserEmail()).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(user).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        refreshTokenInfo.setRefreshToken(user, refreshToken);
        UUID refreshTokenSeq = refreshTokenRepository.save(refreshTokenInfo).getRefreshTokenSeq();
        log.info(">>>>> : {}", refreshTokenInfo.getRefreshTokenSeq());
        return BothTokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshTokenSeq(refreshTokenSeq)
                .build();
    }

    @Transactional(readOnly = true)
    public FindUserInfoResponseDto getUserInfo(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        return FindUserInfoResponseDto.builder()
                .userNickname(user.getUserNickname())
                .major1(user.getMajor1())
                .major2(user.getMajor2())
                .follower(user.getFollower())
                .following(user.getFollowing())
                .introduce(user.getIntroduce())
                .build();
    }

    @Transactional
    public TokenResponseDto loginFlow(LoginRequestDto loginRequestDto) throws Exception {
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginRequestDto.getUserEmail());
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUserEmail());

        //nickname 또는 major 1 또는 major 2 가 비어있으면 회원가입
        if ("".equals(loginRequestDto.getUserNickname()) && "".equals(loginRequestDto.getMajor1()) && "".equals(loginRequestDto.getMajor2())
                && !userRepository.existsByUserEmail(loginRequestDto.getUserEmail())) {
            return TokenResponseDto.builder()
                    .accessToken("발급x")
                    .refreshTokenSeq(UUID.randomUUID())
                    .loginStatus(LoginStatus.회원가입)
                    .userNickname("")
                    .major1("")
                    .major2("")
                    .follower(0)
                    .following(0)
                    .build();
        }
        //기존 회원이 있다면
        if (userRepository.existsByUserEmail(loginRequestDto.getUserEmail())) {
            User userInfo = userRepository.findByUserEmail(loginRequestDto.getUserEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + loginRequestDto.getUserEmail()));
            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo).orElseThrow(() ->
                    new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
            refreshTokenInfo.setRefreshToken(userInfo, refreshToken);
            UUID refreshTokenSeq = refreshTokenRepository.save(refreshTokenInfo).getRefreshTokenSeq();
            log.info(">>>>> : {}", refreshTokenSeq);

            //로그인 이력 업데이트
            userInfo.setLastLoginDate(LocalDate.now());
            userRepository.save(userInfo);

            return TokenResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshTokenSeq(refreshTokenSeq)
                    .loginStatus(LoginStatus.로그인)
                    .userNickname(userInfo.getUserNickname())
                    .major1(userInfo.getMajor1())
                    .major2(userInfo.getMajor2())
                    .follower(userInfo.getFollower())
                    .following(userInfo.getFollowing())
                    .build();
        }
        //신규 회원이라면
        userRepository.save(
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
        User user = userRepository.findByUserEmail(loginRequestDto.getUserEmail()).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshTokenInfo = new RefreshToken();
        refreshTokenInfo.setRefreshToken(user, refreshToken);
        UUID refreshTokenSeq = refreshTokenRepository.save(refreshTokenInfo).getRefreshTokenSeq();
        log.info(">>>>> : {}", refreshTokenSeq);
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshTokenSeq(refreshTokenSeq)
                .loginStatus(LoginStatus.회원가입완료)
                .userNickname("")
                .major1("")
                .major2("")
                .follower(0)
                .following(0)
                .build();
    }

    public SearchUserInfoResponseDto searchUserInfo(String userNickname) {
        User user = userRepository.findByUserNickname(userNickname).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        return SearchUserInfoResponseDto.builder()
                .userEmail(user.getUserEmail())
                .userNickname(userNickname)
                .major1(user.getMajor1())
                .major2(user.getMajor2())
                .follower(user.getFollower())
                .following(user.getFollowing())
                .build();
    }
}
