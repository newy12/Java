package com.summar.gateway.results;

import com.summar.gateway.auth.LoginUser;
import org.springframework.http.ResponseEntity;

public class AuthenticationResult {



    public static ResponseEntity<ApiResult> build(LoginUser loginUser) throws Exception {
        ApiResult apiResult = ApiResult.blank()
            .add("accessToken", loginUser.getAccessToken())
            .add("refreshToken",loginUser.getRefreshToken());
        return Result.ok(apiResult);
    }
}
