package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.TokenResponseDto;
import com.summar.summar.repository.RefreshTokenRepository;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveRefreshTokenInfo(String userEmail, RefreshToken refreshToken, TokenResponseDto tokenResponseDto) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        refreshToken.setRefreshToken(user,tokenResponseDto.getRefreshToken());
        refreshTokenRepository.save(refreshToken);
        log.info(">>>>> : {}", refreshToken.getRefreshTokenSeq());
    }
    @Transactional
    public RefreshToken getRefreshTokenInfo(User user,String refreshToken) {
        return refreshTokenRepository.findByUserAndRefreshToken(user,refreshToken).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
    }
    @Transactional
    public RefreshToken getRefreshTokenInfo(User user) {
        return refreshTokenRepository.findByUser(user).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
    }

    @Transactional
    public RefreshToken getRefreshTokenInfo(UUID refreshTokenId) {
        return refreshTokenRepository.findByRefreshTokenSeq(refreshTokenId).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
    }

    @Transactional
    public RefreshToken deleteByRefreshTokenSeq(UUID refreshTokenSeq) {
        return refreshTokenRepository.deleteByRefreshTokenSeq(refreshTokenSeq).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
    }

    public void saveNewRefreshTokenInfo(String userEmail,TokenResponseDto tokenResponseDto) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(user,tokenResponseDto.getRefreshToken());
        refreshTokenRepository.save(refreshToken);
        log.info(">>>>> : {}", refreshToken.getRefreshTokenSeq());
    }
}
