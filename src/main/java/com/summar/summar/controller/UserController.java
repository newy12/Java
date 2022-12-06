package com.summar.summar.controller;

import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.LoginRequestDto;
import com.summar.summar.dto.MajorResponseDto;
import com.summar.summar.dto.TokenResponseDto;
import com.summar.summar.results.ApiResult;
import com.summar.summar.results.AuthenticationResult;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.service.RefreshTokenService;
import com.summar.summar.service.UserService;
import com.summar.summar.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"유저 관련 API 제공 controller"})
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final RedisTemplate redisTemplate;
    private final RefreshTokenService refreshTokenService;


    /**
     * 로그인 & 회원가입
     * @param loginRequestDto
     * @return
     * @throws Exception
     */

    @Operation(summary = "회원가입 & 로그인", description = "토큰이 발급 됩니다,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJleHAiOjE2NzAyODU0NDIsImlhdCI6MTY3MDI4NTE0Mn0.j7_r4hhSVyQjLR7sTw8GQyBix1Md04akE-256qSOHW4\",\n" +
                    "  \"refreshToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJleHAiOjE2NzAzMjExNDIsImlhdCI6MTY3MDI4NTE0Mn0.hDbqlzHfe1oGV-TOAGvaGJAT3L62Uw6eZk6IOZTAmDk\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResult> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginRequestDto.getUserEmail());
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUserEmail());
        //기존 회원이 있다면
        if(userService.checkUserEmail(loginRequestDto.getUserEmail())){
            tokenResponseDto.setAccessToken(accessToken);
            tokenResponseDto.setRefreshToken(refreshToken);
            RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(userService.findUserInfo(loginRequestDto.getUserEmail()));
            refreshTokenService.saveRefreshTokenInfo(loginRequestDto.getUserEmail(),refreshTokenInfo,tokenResponseDto);
            //로그인 이력 업데이트
            User userInfo = userService.findByUserId(loginRequestDto.getUserEmail());
            userService.updateLastUserLoginDate(userInfo);

            return AuthenticationResult.build(tokenResponseDto);
        }
        //신규 회원이라면.
        tokenResponseDto.setAccessToken(accessToken);
        tokenResponseDto.setRefreshToken(refreshToken);
        userService.saveUser(loginRequestDto);
        refreshTokenService.saveNewRefreshTokenInfo(loginRequestDto.getUserEmail(),tokenResponseDto);
        //로그인 이력 업데이트
        User userInfo = userService.findByUserId(loginRequestDto.getUserEmail());
        userService.updateLastUserLoginDate(userInfo);

        return AuthenticationResult.build(tokenResponseDto);
    }

    /**
     * 로그아웃
     *
     * @param token
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<BooleanResult> logout(@RequestHeader(value = "Authorization") String token) {

        // 레디스에 토큰 값 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("accessToken", token);

        log.info("redis token  : {}", valueOperations.get("accessToken"));

        return BooleanResult.build("result", jwtUtil.validateRedisToken(valueOperations.get("accessToken")), "message", null);
    }
    /**
     * 필명 중복체크
     * @param nickname
     * @return
     * @throws NoSuchAlgorithmException
     */
    @GetMapping("/nicknameCheck/{nickname}")
    public ResponseEntity<Boolean> checkNicknameDuplication(@PathVariable String nickname) throws NoSuchAlgorithmException {
        if (nickname.isEmpty()) {
            throw new NullPointerException();
        }
        return ResponseEntity.ok(userService.checkNicknameDuplication(nickname));
    }


    @GetMapping("/major")
    public ResponseEntity<List<MajorResponseDto>> getParentsMajor(){

        List<MajorResponseDto> majorList = userService.findParentsMajor();
        return ResponseEntity.ok(majorList);
    }

    @GetMapping("/major/{majorSeq}")
    public ResponseEntity<List<MajorResponseDto>> getChildMajor(@PathVariable Long majorSeq){
        if (majorSeq == null) {
            throw new NullPointerException();
        }
        List<MajorResponseDto> majorList = userService.findChildMajorByParentsSeq(majorSeq);
        return ResponseEntity.ok(majorList);
    }
}
