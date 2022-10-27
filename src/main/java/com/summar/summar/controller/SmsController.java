package com.summar.summar.controller;

import com.summar.summar.dto.SmsRequestDto;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.service.SmsService;
import com.summar.summar.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/sms")
public class SmsController {
    private final SmsService smsService;
    private final UserService userService;

    /**
     * 휴대번호 중복체크 후 메세지 전송
     * @param smsRequestDto
     * @return
     */
    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody SmsRequestDto smsRequestDto) throws Exception {
        List<String> result = new ArrayList<>();
        //휴대폰 중복체크.
        if(userService.userHpNoDuplication(smsRequestDto)){
            result.add("휴대폰번호 중복입니다.");
            return BooleanResult.build("userHpNoDuplication", true, "message", result);
        }
        //문자 전송
        result.add(smsService.send(smsRequestDto));
        result.add("정상적으로 문자 전송 완료");
        return BooleanResult.build("userHpNoDuplication", false, "message", result);
    }
}
