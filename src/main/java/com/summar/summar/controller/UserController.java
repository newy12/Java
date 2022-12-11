package com.summar.summar.controller;

import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.FindUserInfoResponseDto;
import com.summar.summar.dto.LoginRequestDto;
import com.summar.summar.dto.LoginStatus;
import com.summar.summar.dto.TokenResponseDto;
import com.summar.summar.results.*;
import com.summar.summar.service.RefreshTokenService;
import com.summar.summar.service.UserService;
import com.summar.summar.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
                    "  \"major2\": \"컴퓨터ㆍ통신\",\n" +
                    "  \"major1\": \"공학계열\",\n" +
                    "  \"userNickname\": \"욱승\",\n" +
                    "  \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNTQ5NTQ5ODM3IiwiZXhwIjoxNjcwNzY2NjE2LCJpYXQiOjE2NzA3NjYzMTZ9.c2SHSHtUgtdHDjHvkP-SD16tcQ5ZIQG3-6onGIxnF-0\",\n" +
                    "  \"loginStatus\": \"로그인\",\n" +
                    "  \"refreshToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNTQ5NTQ5ODM3IiwiZXhwIjoxNjcwODAyMzE2LCJpYXQiOjE2NzA3NjYzMTZ9.rnmL0ZUDudyjmo9IYPhXfZVpsiW0ENQahKSgdw7MY3k\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResult> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginRequestDto.getUserEmail());
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUserEmail());

        //nickname 혹은 major 1 혹은 major 2 가 비어있으면 회원가입
        if("".equals(loginRequestDto.getUserNickName()) && "".equals(loginRequestDto.getMajor1()) && "".equals(loginRequestDto.getMajor2())
        && !userService.checkUserEmail(loginRequestDto.getUserEmail())){
            return AuthenticationResult.build(
                    TokenResponseDto.builder()
                            .accessToken("발급X")
                            .refreshToken("발급x")
                            .loginStatus(LoginStatus.회원가입)
                            .userNickname("")
                            .major1("")
                            .major2("")
                    .build());
        }

        //기존 회원이 있다면
        if(userService.checkUserEmail(loginRequestDto.getUserEmail())){
            User userInfo = userService.findByUserId(loginRequestDto.getUserEmail());
            RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(userService.findUserInfo(loginRequestDto.getUserEmail()));
            refreshTokenService.saveRefreshTokenInfo(loginRequestDto.getUserEmail(),refreshTokenInfo,
                    TokenResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .loginStatus(LoginStatus.로그인)
                            .userNickname(userInfo.getUserNickname())
                            .major1(userInfo.getMajor1())
                            .major2(userInfo.getMajor2())
                            .build());
            //로그인 이력 업데이트
            userService.updateLastUserLoginDate(userInfo);
            return AuthenticationResult.build(
                    TokenResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .loginStatus(LoginStatus.로그인)
                            .userNickname(userInfo.getUserNickname())
                            .major1(userInfo.getMajor1())
                            .major2(userInfo.getMajor2())
                            .build());
        }
        //신규 회원이라면.
        userService.saveUser(loginRequestDto);
        refreshTokenService.saveNewRefreshTokenInfo(loginRequestDto.getUserEmail(),TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .loginStatus(LoginStatus.회원가입완료)
                .build());
        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .loginStatus(LoginStatus.회원가입완료)
                .userNickname("")
                .major1("")
                .major2("")
                .build();
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

    @Operation(summary = "이메일로 회원 정보 찾기", description = "회원닉네임,전공(1,2) 찾아옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"result\": {\n" +
                    "        \"userNickname\": \"욱승\",\n" +
                    "        \"major1\": \"공학계열\",\n" +
                    "        \"major2\": \"컴퓨터ㆍ통신\"\n" +
                    "    }\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/find-user")
    public ResponseEntity<?> findUserInfo(@RequestParam(value = "userEmail")String userEmail){
        User user = userService.findUserInfo(userEmail);
        FindUserInfoResponseDto findUserInfoResponseDto = FindUserInfoResponseDto.builder()
                .userNickname(user.getUserNickname())
                .major1(user.getMajor1())
                .major2(user.getMajor2())
                .build();
        return ObjectResult.build("result",findUserInfoResponseDto);
    }

    /**
     * 필명 중복체크
     *
     * @param nickname
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Operation(summary = "닉네임 중복 체크", description = "닉네임의 중복을 체크합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"status\": \"SUCCESS\",\n" +
                    "  \"message\": \"정상처리\",\n" +
                    "  \"errorMessage\": null,\n" +
                    "  \"errorCode\": null,\n" +
                    "  \"result\": {\n" +
                    "    \"result\": true\n" +
                    "  }\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/nicknameCheck/{nickname}")
    public ResponseEntity<?> checkNicknameDuplication(@PathVariable String nickname) throws NoSuchAlgorithmException {
        if (nickname.isEmpty()) {
            throw new NullPointerException();
        }
        return BooleanResult.build("result",userService.checkNicknameDuplication(nickname));
    }


    @GetMapping("/major")
    public ResponseEntity<?> getParentsMajor(){
        return ListResult.build("result",userService.findParentsMajor());
    }

    @GetMapping("/major/{majorSeq}")
    public ResponseEntity<?> getChildMajor(@PathVariable Long majorSeq){
        if (majorSeq == null) {
            throw new NullPointerException();
        }
        return ListResult.build("result",userService.findChildMajorByParentsSeq(majorSeq));
    }
}
