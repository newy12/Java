package com.summar.summar.controller;

import com.summar.summar.enumeration.SettingType;
import com.summar.summar.repository.SettingRepository;
import com.summar.summar.results.ListResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/setting")
public class SettingController {

    private final SettingRepository settingRepository;


    /**
     * 공지사항
     */
    @GetMapping("/notice")
    public ResponseEntity<?> notice() {
       return ListResult.build("results",settingRepository.findAllBySettingType(SettingType.NOTICE));
    }
    /**
     * 자주 묻는 질문
     */
    @GetMapping("/question")
    public ResponseEntity<?> question() {
        return ListResult.build("results",settingRepository.findAllBySettingType(SettingType.QUESTION));
    }
}
