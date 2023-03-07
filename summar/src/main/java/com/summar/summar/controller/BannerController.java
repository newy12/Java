package com.summar.summar.controller;

import com.summar.summar.results.ObjectResult;
import com.summar.summar.service.BannerService;
import com.summar.summar.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"배너 관련 API 제공 controller"})
@RequestMapping(value = "/api/v1/banner")
public class BannerController {

    private final BannerService bannerService;

    /**
     * 배너 이미지 정보 추출
     * @return
     */
    @Operation(summary = "배너 이미지 정보 추출", description = "배너 이미지 url 획득합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.selectBanners))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public ResponseEntity<?> selectBanners(){
       return ObjectResult.build("results",bannerService.findAllByBanner());
    }
}
