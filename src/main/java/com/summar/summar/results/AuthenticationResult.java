package com.summar.summar.results;

import com.summar.summar.auth.LoginUser;
import com.summar.summar.dto.TokenResponseDto;
import org.springframework.http.ResponseEntity;

public class AuthenticationResult {



    public static ResponseEntity<ApiResult> build(TokenResponseDto tokenResponseDto) throws Exception {
        ApiResult apiResult = ApiResult.blank()
            .add("accessToken", tokenResponseDto.getAccessToken() == null ? null :  tokenResponseDto.getAccessToken())
            .add("refreshToken",tokenResponseDto.getRefreshToken() == null ? null : tokenResponseDto.getRefreshToken())
                .add("loginStatus",tokenResponseDto.getLoginStatus());
        return Result.ok(apiResult);
    }
}
