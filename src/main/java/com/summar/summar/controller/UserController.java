package com.summar.summar.controller;

import com.summar.summar.auth.LoginUser;
import com.summar.summar.auth.SummarUser;
import com.summar.summar.common.CurrentUser;
import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.*;
import com.summar.summar.results.*;
import com.summar.summar.service.CustomUserDetailService;
import com.summar.summar.service.RefreshTokenService;
import com.summar.summar.service.UserService;
import com.summar.summar.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RedisTemplate redisTemplate;
    private final RefreshTokenService refreshTokenService;










    /**
     * 로그인
     *
     * @param loginRequestDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public ResponseEntity<ApiResult> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword())
        );*/
        //final SummarUser customUser = (SummarUser) customUserDetailService.loadUserByUsername(loginRequestDto.getUserEmail());
        //LoginUser loginUser = new LoginUser();

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginRequestDto.getUserEmail());
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUserEmail());
        //기존 회원이 있다면.
        if(userService.checkUserEmail(loginRequestDto.getUserEmail())){
            refreshTokenService.saveRefreshTokenInfo(loginRequestDto.getUserEmail(),refreshToken);
            RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(userService.findUserInfo(loginRequestDto.getUserEmail()),refreshToken);
            tokenResponseDto.setAccessToken(accessToken);
            tokenResponseDto.setRefreshToken(String.valueOf(refreshTokenInfo.getRefreshTokenSeq()));
            return AuthenticationResult.build(tokenResponseDto);
        }
        //신규 회원이라면.
        User user = userService.saveUser(loginRequestDto);
        refreshTokenService.saveRefreshTokenInfo(loginRequestDto.getUserEmail(), refreshToken);
        RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(user, refreshToken);
        tokenResponseDto.setAccessToken(accessToken);
        tokenResponseDto.setRefreshToken(String.valueOf(refreshTokenInfo.getRefreshTokenSeq()));

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

    /*@PostMapping("/join")
    public ResponseEntity<BooleanResult> join(@Validated @RequestBody JoinRequestDto joinRequestDto, Errors error) throws Exception {
        //벨리데이션 체크
        List<String> result = new ArrayList<>();
        if (error.hasErrors()) {
            List<String> errorList = new ArrayList<>();
            for (ObjectError errors : error.getAllErrors()) {
                errorList.add(errors.getDefaultMessage());
            }
            log.info("회원가입 벨리데이션 결과: {}", errorList);
            return BooleanResult.build("result", false, "message", errorList);
        }
        //유저 저장
        Boolean saveResult = userService.saveUser(joinRequestDto);
        if (saveResult) {
            result.add("회원가입 성공");
            return BooleanResult.build("result", true, "message", result);
        } else {
            result.add("회원가입 실패");
            return BooleanResult.build("result", false, "message", result);
        }
    }*/

    /**
     * 토큰재발급
     *
     * @param user
     * @param refreshTokenRequestDto
     * @return
     */
    /*@PreAuthorize("isAuthenticated()")
    @PostMapping("/refreshToken")
    public ResponseEntity<ListResult> refreshToken(User user, @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        //RefreshToken 객체 추출
        RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(refreshTokenRequestDto.getRefreshTokenId());
        //RefreshToken 유효기간 체크
        if (jwtUtil.validateRefreshToken(refreshTokenInfo.getRefreshToken(), user)) {
            String accessToken = jwtUtil.generateToken(user.getLoginUser());
            List<String> result = new ArrayList<>();
            result.add(accessToken);
            return ListResult.build("result", result);
        } else {
            //기존 RefreshToken 삭제
            refreshTokenService.deleteByRefreshTokenSeq(refreshTokenInfo.getRefreshTokenSeq());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }*/

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
    /*@GetMapping("/userIdCheck/{userId}")
    public ResponseEntity<Boolean> checkUserIdDuplication(@PathVariable String userId) throws NoSuchAlgorithmException {
        if (userId.isEmpty()) {
            throw new NullPointerException();
        }
        return ResponseEntity.ok(userService.checkUserIdDuplication(userId));
    }*/

    /*@PostMapping("/find-id")
    public ResponseEntity<?> findId(@RequestBody FindRequestDto findRequestDto) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String userId = userService.getUserInfo(findRequestDto.getUserHpNo()).getUserId();
        StringBuilder resultUserId = new StringBuilder();
        if(findRequestDto.getFindIdFlag().equals("notCert")){
            //아이디 앞에 세글자 제외한 나머지 문자 * 치환
            resultUserId.append(userId, 0, 3);
            for (int i = 0; i < userId.length()-3; i++) {
                resultUserId.append("*");
            }
            return ObjectResult.build("result",resultUserId);
        }
        //아이디 그대로 반환
        resultUserId.append(userId);
        return ObjectResult.build("result",resultUserId);
    }
    @PostMapping("/find-pw")
    public void passwordReset(@RequestBody FindRequestDto findRequestDto) {

    }*/
}
