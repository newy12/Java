package com.summar.summar.controller;

import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.results.ListResult;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"피드 API controller"})
@RequestMapping(value = "/api/v1/feed")
public class FeedController {
    private final FeedService feedService;


    @Operation(summary = "피드 등록")
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"message\": \"정상처리\",\n" +
                    "    \"errorMessage\": null,\n" +
                    "    \"errorCode\": null,\n" +
                    "    \"result\": {\n" +
                    "                \"feedSeq\": 1,\n" +
                    "                \"userSeq\": 1,\n" +
                    "                \"contents\": \"dd\",\n" +
                    "                \"feedImages\": [{\n" +
                    "                       \"feedImageSeq\": 1,\n" +
                    "                       \"feedSeq\": 1,\n" +
                    "                       \"imageUrl\": 1,\n" +
                    "                       \"orderNo\": 1,\n" +
                    "                   }]\n" +
                    "                \"secretYn\": true,\n" +
                    "                \"commentYn\": true,\n" +
                    "                \"tempSaveYn\": true,\n" +
                    "            }"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    public ResponseEntity<?> registFeed(@ModelAttribute FeedRegisterDto feedRegisterDto) {
        return ObjectResult.build("result", feedService.saveFeed(feedRegisterDto));
    }

    @Operation(summary = "피드 조회")
    @GetMapping(value = "")
   /* @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"message\": \"정상처리\",\n" +
                    "    \"errorMessage\": null,\n" +
                    "    \"errorCode\": null,\n" +
                    "    \"result\": {\n" +
                    "        \"result\": [\n" +
                    "            {\n" +
                    "                \"feedSeq\": 1,\n" +
                    "                \"userSeq\": 1,\n" +
                    "                \"contents\": \"dd\",\n" +
                    "                \"imageUrls\": []\n" +
                    "                \"commentYn\": \"dd\",\n" +
                    "            }\n" +
                    "            ]\n" +
                    "     }\n"
                ))),

            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })*/
    @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null")))
    public ResponseEntity<List<FeedDto>> getFeed() {
        return ResponseEntity.ok().body(feedService.getFeed());
    }

}
