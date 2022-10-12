package com.summar.gateway.controller;

import com.summar.gateway.auth.LoginUser;
import com.summar.gateway.auth.SummarUser;
import com.summar.gateway.common.CurrentUser;
import com.summar.gateway.config.RedisConfig;
import com.summar.gateway.domain.RefreshToken;
import com.summar.gateway.dto.LoginRequestDto;
import com.summar.gateway.dto.RefreshTokenRequestDto;
import com.summar.gateway.results.*;
import com.summar.gateway.service.RefreshTokenService;
import com.summar.gateway.util.JwtUtil;
import com.summar.gateway.domain.User;
import com.summar.gateway.repository.UserRepository;
import com.summar.gateway.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class LoginController {
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
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

        return BooleanResult.build("result",jwtUtil.validateRedisToken(valueOperations.get("accessToken")));
    }

    /**
     * 토큰재발급
     * @param user
     * @param refreshTokenRequestDto
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@CurrentUser SummarUser user,@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        //RefreshToken 객체 추출
        RefreshToken refreshTokenInfo = refreshTokenService.getRefreshTokenInfo(refreshTokenRequestDto.getRefreshTokenId());
        //RefreshToken 유효기간 체크
        if(jwtUtil.validateRefreshToken(refreshTokenInfo.getRefreshToken(),user)){
            String accessToken = jwtUtil.generateToken(user.getLoginUser());
            List<String> result = new ArrayList<>();
            result.add(accessToken);
            return ListResult.build("result",result);
        }else{
            //기존 RefreshToken 삭제
            refreshTokenService.deleteByRefreshTokenSeq(refreshTokenInfo.getRefreshTokenSeq());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


//반환값 list 테스트
    @PostMapping(value = "/test")
    public ResponseEntity<?> test(){
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
        list.add("test5");
        return ListResult.build("results",list);
    }

    //redis 연동 테스트
    @Cacheable(value = "test")
    @GetMapping(value = "/redisadd")
    public void redisadd(){
        userRepository.findAll();
    }

    //데이터 테스트용으로 만듬
    @PostMapping( "/adduser")
    public void userAdd() {
        User user = User.builder()
                .userId("newy12")
                .userPwd(passwordEncoder.encode("123"))
                .userHpNo("01057212058")
                .userEmail("newy12@naver.com")
                .userName("김영재")
                .userNickname("영재킴")
                .build();

        userRepository.save(user);
    }
}
