package com.summar.summar.controller;

import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.*;
import com.summar.summar.results.ApiResult;
import com.summar.summar.results.AuthenticationResult;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.service.RefreshTokenService;
import com.summar.summar.service.UserService;
import com.summar.summar.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

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
    private final AuthenticationManager authenticationManager;


    /**
     * 로그인 & 회원가입
     *
     * @param loginRequestDto
     * @return
     * @throws Exception
     */

    @Operation(summary = "회원가입 & 로그인", description = "토큰이 발급 됩니다,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"major2\": \"전공2\",\n" +
                    "    \"major1\": \"전공1\",\n" +
                    "    \"follower\": 0,\n" +
                    "    \"following\": 0,\n" +
                    "    \"userNickname\": \"4124\",\n" +
                    "    \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd5MTMiLCJleHAiOjE2NzExMTc4MDEsImlhdCI6MTY3MTExNzUwMX0.SUBYajqxbX7JA4v2iBvMUUpGhWCnM4PY3pSMBPv-7jI\",\n" +
                    "    \"loginStatus\": \"로그인\",\n" +
                    "    \"refreshToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd5MTMiLCJleHAiOjE2NzExNTM1MDEsImlhdCI6MTY3MTExNzUwMX0.fOZj98iGiMr1KY4q2MszAddhcX1u8Ko3ds7K6Fl1rcU\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResult> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginRequestDto.getUserEmail());
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUserEmail());

        //nickname 또는 major 1 또는 major 2 가 비어있으면 회원가입
        if ("".equals(loginRequestDto.getUserNickname()) && "".equals(loginRequestDto.getMajor1()) && "".equals(loginRequestDto.getMajor2())
                && !userService.checkUserEmail(loginRequestDto.getUserEmail())) {
            return AuthenticationResult.build(
                    TokenResponseDto.builder()
                            .accessToken("발급x")
                            .refreshTokenSeq(UUID.randomUUID())
                            .loginStatus(LoginStatus.회원가입)
                            .userNickname("")
                            .major1("")
                            .major2("")
                            .follower(0)
                            .following(0)
                            .build());
        }
        //기존 회원이 있다면
        if (userService.checkUserEmail(loginRequestDto.getUserEmail())) {
            User userInfo = userService.findByUserId(loginRequestDto.getUserEmail());
            RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(userService.findUserInfo(loginRequestDto.getUserEmail()));
            UUID refreshTokenSeq = refreshTokenService.saveRefreshTokenInfo(loginRequestDto.getUserEmail(), refreshTokenInfo, refreshToken);
            //로그인 이력 업데이트
            userService.updateLastUserLoginDate(userInfo);

            return AuthenticationResult.build(
                    TokenResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshTokenSeq(refreshTokenSeq)
                            .loginStatus(LoginStatus.로그인)
                            .userNickname(userInfo.getUserNickname())
                            .major1(userInfo.getMajor1())
                            .major2(userInfo.getMajor2())
                            .follower(userInfo.getFollower())
                            .following(userInfo.getFollowing())
                            .build());
        }
        //신규 회원이라면
        userService.saveUser(loginRequestDto);
        UUID refreshTokenSeq = refreshTokenService.saveNewRefreshTokenInfo(loginRequestDto.getUserEmail(), refreshToken);
        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshTokenSeq(refreshTokenSeq)
                .loginStatus(LoginStatus.회원가입완료)
                .userNickname("")
                .major1("")
                .major2("")
                .follower(0)
                .following(0)
                .build();
        return AuthenticationResult.build(tokenResponseDto);
    }

    /**
     * 리프레시 토큰으로 엑세스 토큰 재발급
     *
     * @param refreshTokenRequestDto
     * @return
     */
    @PostMapping("/give-access-token")
    public ResponseEntity<?> giveAccessToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenInfo(userService.findUserInfo(refreshTokenRequestDto.getUserEmail()));
        if (jwtUtil.validateRefreshToken(refreshToken.getRefreshToken(), refreshTokenRequestDto.getUserEmail())) {
            String newAccessToken = jwtUtil.generateToken(refreshTokenRequestDto.getUserEmail());
            return ObjectResult.build("accessToken", newAccessToken);
        }
        return null;
    }

    /**
     * 액세스 토큰, 리프레시 토큰 재발급
     *
     * @param refreshTokenRequestDto
     * @return
     */
    @PostMapping("/give-both-token")
    public ResponseEntity<?> giveBothToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        String accessToken = jwtUtil.generateToken(refreshTokenRequestDto.getUserEmail());
        String refreshToken = jwtUtil.generateRefreshToken(refreshTokenRequestDto.getUserEmail());
        RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(userService.findUserInfo(refreshTokenRequestDto.getUserEmail()));
        UUID refreshTokenSeq = refreshTokenService.saveRefreshTokenInfo(refreshTokenRequestDto.getUserEmail(), refreshTokenInfo, refreshToken);
        return ObjectResult.build("results", BothTokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshTokenSeq(refreshTokenSeq)
                .build());
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

    @Operation(summary = "이메일로 회원 정보 찾기", description = "회원의 모든 정보를 찾아옵니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"result\": {\n" +
                    "        \"userNickname\": \"dd\",\n" +
                    "        \"major1\": \"전공1\",\n" +
                    "        \"major2\": \"전공2\",\n" +
                    "        \"follower\": 0,\n" +
                    "        \"following\": 0\n" +
                    "    }\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user-info")
    public ResponseEntity<?> findUserInfo(@RequestParam(value = "userEmail") String userEmail) {
        User user = userService.findUserInfo(userEmail);
        FindUserInfoResponseDto findUserInfoResponseDto = FindUserInfoResponseDto.builder()
                .userNickname(user.getUserNickname())
                .major1(user.getMajor1())
                .major2(user.getMajor2())
                .follower(user.getFollower())
                .following(user.getFollowing())
                .introduce(user.getIntroduce())
                .build();
        return ObjectResult.build("result", findUserInfoResponseDto);
    }

    /**
     * 마이페이지 개인정보 수정
     *
     * @param changeUserInfoRequestDto
     * @return
     */
    @Operation(summary = "마이페이지 개인정보 수정", description = "회원의 정보를 수정합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"message\": \"정상처리\",\n" +
                    "    \"errorMessage\": \"\",\n" +
                    "    \"errorCode\": \"\",\n" +
                    "    \"result\": null\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/user-info")
    public ResponseEntity<?> changeUserInfo(@RequestBody ChangeUserInfoRequestDto changeUserInfoRequestDto) {
        userService.changeUserInfo(changeUserInfoRequestDto);
        return ObjectResult.ok();
    }

    /**
     * 자기소개 작성
     *
     * @param addIntroduceRequestDto
     * @return
     */
    @Operation(summary = "자기소개 추가", description = "자기소개를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"message\": \"정상처리\",\n" +
                    "    \"errorMessage\": \"\",\n" +
                    "    \"errorCode\": \"\",\n" +
                    "    \"result\": null\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-introduce")
    public ResponseEntity<?> addIntroduce(@RequestBody AddIntroduceRequestDto addIntroduceRequestDto) {
        userService.addIntroduce(addIntroduceRequestDto);
        return ObjectResult.ok();
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
    @GetMapping("/nickname-check")
    public ResponseEntity<?> checkNicknameDuplication(@RequestParam(value = "nickname") String nickname) throws NoSuchAlgorithmException {
        if (nickname.isEmpty()) {
            throw new NullPointerException();
        }
        return BooleanResult.build("result", userService.checkNicknameDuplication(nickname));
    }
}
