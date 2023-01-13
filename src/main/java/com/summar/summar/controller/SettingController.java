package com.summar.summar.controller;

import com.summar.summar.enumeration.SettingType;
import com.summar.summar.repository.SettingRepository;
import com.summar.summar.results.ListResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/setting")
public class SettingController {

    private final SettingRepository settingRepository;


    /**
     * 설정 페이지
     */
    @GetMapping("")
    public ResponseEntity<?> setting(@RequestParam(value = "status")String status) {
        if("notice".equals(status)){
            return ListResult.build("results",settingRepository.findAllBySettingType(SettingType.NOTICE));
        }
        if("question".equals(status)){
            return ListResult.build("results",settingRepository.findAllBySettingType(SettingType.QUESTION));
        }
        else{
            throw new NotFoundException("없는 타입입니다.");
        }

    }

}
