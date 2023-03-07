package com.summar.summar.results;

import com.summar.summar.dto.TokenResponseDto;
import org.springframework.http.ResponseEntity;

public class AuthenticationResult {


    public static ResponseEntity<ApiResult> build(TokenResponseDto tokenResponseDto) throws Exception {
        ApiResult apiResult = ApiResult.blank()
                .add("accessToken", tokenResponseDto.getAccessToken() == null ? null : tokenResponseDto.getAccessToken())
                .add("refreshTokenSeq", tokenResponseDto.getRefreshTokenSeq() == null ? null : tokenResponseDto.getRefreshTokenSeq())
                .add("loginStatus", tokenResponseDto.getLoginStatus())
                .add("userSeq",tokenResponseDto.getUserSeq())
                .add("userNickname", tokenResponseDto.getUserNickname() == "" ? "" : tokenResponseDto.getUserNickname())
                .add("major1", tokenResponseDto.getMajor1() == "" ? "" : tokenResponseDto.getMajor1())
                .add("major2", tokenResponseDto.getMajor2() == "" ? "" : tokenResponseDto.getMajor2())
                .add("follower", tokenResponseDto.getFollower())
                .add("following", tokenResponseDto.getFollowing());
        return Result.ok(apiResult);
    }
}
