package com.summar.summar.controller;

import com.summar.summar.results.ObjectResult;
import com.summar.summar.service.GatheringNotificationService;
import com.summar.summar.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class GatheringNotificationController {

    private final GatheringNotificationService gatheringNotificationService;


    /**
     * 알림리스트 조회
     * @param userSeq
     * @return
     */
    @Operation(summary = "해당 유저의 알림리스트 조회", description = "해당 유저의 알림리스트 조회합니다." , security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.notificationList))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public ResponseEntity<?> notificationList(@RequestParam(value = "userSeq")Long userSeq){
        return ObjectResult.build("result",gatheringNotificationService.findByNotificationList(userSeq));
    }
}
