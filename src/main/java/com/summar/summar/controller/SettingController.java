package com.summar.summar.controller;

import com.summar.summar.enumeration.SettingType;
import com.summar.summar.repository.SettingRepository;
import com.summar.summar.results.ListResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "환경설정", description = "parameter = NOTICE 시 = 공지사항 관련 데이터조회,  " +
            "parameter = QUESTION 시 = 자주 묻는 질문 관련 데이터 조회", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "  \"status\": \"SUCCESS\",\n" +
                    "  \"message\": \"정상처리\",\n" +
                    "  \"errorMessage\": null,\n" +
                    "  \"errorCode\": null,\n" +
                    "  \"result\": {\n" +
                    "    \"results\": [\n" +
                    "      {\n" +
                    "        \"settingId\": 1,\n" +
                    "        \"settingType\": \"NOTICE\",\n" +
                    "        \"title\": \"써머 출시 1.0.0 업데이트 안내\",\n" +
                    "        \"content\": \"업데이트 안내입니다. 업데이트 안내입니다.업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다.\",\n" +
                    "        \"regDatetime\": \"2022.01.13\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"settingId\": 2,\n" +
                    "        \"settingType\": \"NOTICE\",\n" +
                    "        \"title\": \"써머 장애 안내\",\n" +
                    "        \"content\": \"업데이트 안내입니다.\",\n" +
                    "        \"regDatetime\": \"2022.01.13\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
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
