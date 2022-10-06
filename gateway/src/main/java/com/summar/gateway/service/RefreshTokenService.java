package com.summar.gateway.service;

import com.summar.gateway.common.SummarCommonException;
import com.summar.gateway.common.SummarErrorCode;
import com.summar.gateway.domain.RefreshToken;
import com.summar.gateway.domain.User;
import com.summar.gateway.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveRefreshTokenInfo(User user, String refreshToken) {
        RefreshToken refreshTokenInfo = new RefreshToken();
        refreshTokenInfo.setRefreshToken(user, refreshToken);
        refreshTokenRepository.save(refreshTokenInfo);
        log.info(">>>>> : {}", refreshTokenInfo.getRefreshTokenSeq());
    }

    @Transactional
    public RefreshToken getRefreshTokenInfo(User user, String refreshToken) {
        return refreshTokenRepository.findByUserAndRefreshToken(user,refreshToken).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
    }
}
