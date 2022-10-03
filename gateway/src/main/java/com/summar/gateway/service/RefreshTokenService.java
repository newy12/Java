package com.summar.gateway.service;

import com.summar.gateway.domain.RefreshToken;
import com.summar.gateway.domain.User;
import com.summar.gateway.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken getRefreshTokenInfo(User user, String refreshToken) {
        RefreshToken refreshTokenInfo = new RefreshToken();
        refreshTokenInfo.setRefreshToken(user, refreshToken);
        refreshTokenRepository.save(refreshTokenInfo);
        return refreshTokenRepository.findById(refreshTokenInfo.getRefreshTokenSeq()).orElseThrow(() -> new IllegalArgumentException("not found refreshTokenSeq"));
    }
}
