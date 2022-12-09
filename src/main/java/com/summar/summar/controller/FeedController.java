package com.summar.summar.controller;

import com.summar.summar.domain.Feed;
import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"피드 API controller"})
@RequestMapping(value = "/api/v1/feed")
public class FeedController {
    private final FeedService feedService;


    @Operation(summary = "피드 등록")
    @PostMapping(value = "")
    public ResponseEntity<?> registFeed(@RequestBody FeedRegisterDto feedRegisterDto) throws Exception {
        FeedDto feedDto = feedService.saveFeed(feedRegisterDto);

        return ObjectResult.build("result", feedDto);
    }

}
