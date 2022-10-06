package com.summar.gateway.controller;

import com.summar.gateway.auth.LoginUser;
import com.summar.gateway.auth.SummarUser;
import com.summar.gateway.common.CurrentUser;
import com.summar.gateway.config.RedisConfig;
import com.summar.gateway.domain.RefreshToken;
import com.summar.gateway.dto.LoginRequestDto;
import com.summar.gateway.results.*;
import com.summar.gateway.service.RefreshTokenService;
import com.summar.gateway.util.JwtUtil;
import com.summar.gateway.domain.User;
import com.summar.gateway.repository.UserRepository;
import com.summar.gateway.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
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
    private final JwtUtil jwtCheck;
    private final RefreshTokenService refreshTokenService;

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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization")String token){

        // 레디스에 토큰 값 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("accessToken",token);

        log.info("redis token  : {}" , valueOperations.get("accessToken"));

        return BooleanResult.build("result",jwtCheck.validateRedisToken(valueOperations.get("accessToken")));
    }



    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/good")
    public Map<String, String> good(@CurrentUser SummarUser user) {
        Map<String,String> map = new HashMap<>();
        map.put("userId",user.getLoginUser().getUserId());
        map.put("userpassword",user.getPassword());
        map.put("username",user.getUsername());
        return map;
    }

    @PostMapping(value = "/useradd")
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
