package com.summar.summar.controller;

import com.summar.summar.dto.*;
import com.summar.summar.results.*;
import com.summar.summar.service.PushService;
import com.summar.summar.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final UserService userService;


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
        return AuthenticationResult.build(userService.loginFlow(loginRequestDto));
    }

    /**
     * 리프레시 토큰으로 엑세스 토큰 재발급
     *
     * @param refreshTokenRequestDto
     * @return
     */
    @Operation(summary = "리프레시토큰이 유효할 시 액새스토큰 재발급", description = "토큰이 재발급 됩니다,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"accessToken\": \"djwdbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZGQiLCJleHAiOjE2NzE2OTkyNjUsImlhdCI6MTY3MTY5ODk2NX0.7exMAI-zaTqu5ouZI6AMfuEmE7bTlvAp9wMlCHvVzoA\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PostMapping("/give-access-token")
    public ResponseEntity<?> giveAccessToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return ObjectResult.build("accessToken",  userService.giveAccessToken(refreshTokenRequestDto));
    }
    /**
     * 액세스 토큰, 리프레시 토큰 재발급
     *
     * @param refreshTokenRequestDto
     * @return
     */
    @Operation(summary = "리프래시 토큰 무효할 시 액세스,리프래시 둘다 재발급", description = "토큰이 재발급 됩니다,")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"results\": {\n" +
                    "        \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOdasasdjeHAiOjE2NzE2OTg2NDcsImlhdCI6MTY3MTY5ODM0N30.ONblIUyQSNyQFB_aQep9mrRGZunP14rxaV8glafC1d8\",\n" +
                    "        \"refreshTokenSeq\": \"102c533a-6e5a-436d-86e7-f144d4ssaa41\"\n" +
                    "    }\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PostMapping("/give-both-token")
    public ResponseEntity<?> giveBothToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return ObjectResult.build("results", userService.giveBothToken(refreshTokenRequestDto));
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
        return ObjectResult.build("result", userService.getUserInfo(userEmail));
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
     * @param userNickname
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
    public ResponseEntity<?> checkNicknameDuplication(@RequestParam(value = "userNickname") String userNickname) throws NoSuchAlgorithmException {
        return BooleanResult.build("result", userService.checkNicknameDuplication(userNickname));
    }

    /**
     * 닉네임으로 유저정보 조회
     * @param userNickname
     * @return
     */
    @Operation(summary = "닉네임으로 유저정보 조회", description = "회원의 정보를 조회합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"results\": {\n" +
                    "    \"userNickname\": \"신승욱\",\n" +
                    "    \"follower\": 0,\n" +
                    "    \"following\": 0,\n" +
                    "    \"userEmail\": \"112468669451266982874\",\n" +
                    "    \"major1\": \"자연계열\",\n" +
                    "    \"major2\": \"수학ㆍ물리ㆍ천문ㆍ지리\"\n" +
                    "  }\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search-user-info")
    public ResponseEntity<?> searchUserInfo(@RequestParam(value = "userNickname")String userNickname){
       return ObjectResult.build("results",userService.searchUserInfo(userNickname));
    }
    @Operation(summary = "한글 초성 & 단어 검색 기능", description = "회원의 닉네임검색 기능입니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"firstPage\": true,\n" +
                    "    \"lastPage\": true,\n" +
                    "    \"totalPageCount\": 1,\n" +
                    "    \"recordsPerPage\": 30,\n" +
                    "    \"content\": [\n" +
                    "        {\n" +
                    "            \"userNickname\": \"승욱\",\n" +
                    "            \"major1\": \"공학계열\",\n" +
                    "            \"major2\": \"건축\",\n" +
                    "            \"follower\": 50,\n" +
                    "            \"following\": 30,\n" +
                    "            \"introduce\": null,\n" +
                    "            \"profileImageUrl\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"userNickname\": \"신승욱\",\n" +
                    "            \"major1\": \"자연계열\",\n" +
                    "            \"major2\": \"수학ㆍ물리ㆍ천문ㆍ지리\",\n" +
                    "            \"follower\": 0,\n" +
                    "            \"following\": 0,\n" +
                    "            \"introduce\": null,\n" +
                    "            \"profileImageUrl\": null\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"totalRecordCount\": 2,\n" +
                    "    \"currentPageNo\": 1\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search-user-list")
    public ResponseEntity<?> searchUserInitialList(@RequestParam(value = "userNickname")String userNickname, @PageableDefault(size = 30) Pageable pageable) {
        return PageResult.build(userService.searchUserList(userNickname,pageable));
    }
    @PostMapping("/push-notification-change")
    public ResponseEntity<?> pushNotification(@RequestBody PushNotificationStatusDto pushNotificationStatusDto){
        userService.changePushNotification(pushNotificationStatusDto);
        return ObjectResult.ok();
    }
    @GetMapping("/push-notification-info")
    public ResponseEntity<?> pushNotificationInfo(@RequestParam(value = "userNickname")String userNickname){
      return ObjectResult.build("result",userService.userPushStatusInfo(userNickname));
    }
}
