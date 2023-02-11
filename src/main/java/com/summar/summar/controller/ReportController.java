package com.summar.summar.controller;

import com.summar.summar.dto.ReportRequestDto;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.service.ReportService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * 신고하기
     * @param reportRequestDto
     * @return
     */
    @Operation(summary = "신고하기", description = "신고합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.report))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = StringUtil.nulls))),
    })
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<?> report(@RequestBody ReportRequestDto reportRequestDto) {
       return BooleanResult.build("results",reportService.insertReport(reportRequestDto));
    }
}
