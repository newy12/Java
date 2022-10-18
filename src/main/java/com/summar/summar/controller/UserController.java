package com.summar.summar.controller;

import com.summar.summar.auth.LoginUser;
import com.summar.summar.auth.SummarUser;
import com.summar.summar.common.CurrentUser;
import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.dto.LoginRequestDto;
import com.summar.summar.dto.RefreshTokenRequestDto;
import com.summar.summar.repository.UserRepository;
import com.summar.summar.results.AuthenticationResult;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.results.ListResult;
import com.summar.summar.service.CustomUserDetailService;
import com.summar.summar.service.RefreshTokenService;
import com.summar.summar.service.UserService;
import com.summar.summar.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RedisTemplate redisTemplate;
    private final RefreshTokenService refreshTokenService;

    /**
     * 로그인
     * @param loginRequestDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword())
        );

        final SummarUser customUser = (SummarUser) customUserDetailService.loadUserByUsername(loginRequestDto.getUsername());
        LoginUser loginUser = customUser.getLoginUser();
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginUser);
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginUser);
        refreshTokenService.saveRefreshTokenInfo(loginUser.getUser(),refreshToken);
        RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(loginUser.getUser(),refreshToken);
        loginUser.setAccessToken(accessToken);
        loginUser.setRefreshToken(String.valueOf(refreshTokenInfo.getRefreshTokenSeq()));

        return AuthenticationResult.build(loginUser);
    }




    /**
     * 로그아웃
     * @param token
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization")String token){

        // 레디스에 토큰 값 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("accessToken",token);

        log.info("redis token  : {}" , valueOperations.get("accessToken"));

        return BooleanResult.build("result",jwtUtil.validateRedisToken(valueOperations.get("accessToken")),"message",null);
    }

    /**
     * 회원가입
     * @param joinRequestDto
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated @RequestBody JoinRequestDto joinRequestDto , Errors error) throws NoSuchAlgorithmException {
        //벨리데이션 체크
        if (error.hasErrors()) {
            List<String> errorList = new ArrayList<>();
            for (ObjectError errors:error.getAllErrors()) {
                errorList.add(errors.getDefaultMessage());
            }
            log.info("회원가입 벨리데이션 결과: {}", errorList);
            return BooleanResult.build("result",false,"message",errorList);
        }
        //유저 저장
            userService.saveUser(joinRequestDto);
            return BooleanResult.build("result", true,"message",null);
    }
    /**
     * 토큰재발급
     *
     * @param user
     * @param refreshTokenRequestDto
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@CurrentUser SummarUser user, @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
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
    }
    //redis 연동 테스트
    @Cacheable(value = "test")
    @GetMapping(value = "/redisadd")
    public void redisadd(){
        userRepository.findAll();
    }

    @PostMapping("/ADD")
    public void userADD(){
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setUserHpNo("123");
        joinRequestDto.setUserName("123");
        joinRequestDto.setUserNickname("123");
        joinRequestDto.setUserPwd("123");
        joinRequestDto.setUserId("123");
        userRepository.save(new User(joinRequestDto));
    }
}
