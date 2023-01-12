package com.summar.summar.controller;

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


    @GetMapping("")
    public ResponseEntity<?> setting() {
       return ListResult.build("results",settingRepository.findAll());
    }
}
