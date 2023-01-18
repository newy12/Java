package com.summar.summar.controller;

import com.summar.summar.dto.FeedDto;
import com.summar.summar.dto.FeedRegisterDto;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.results.PageResult;
import com.summar.summar.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"피드 API controller"})
@RequestMapping(value = "/api/v1/feed")
public class FeedController {
    private final FeedService feedService;


    @Operation(summary = "피드 등록")
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<FeedDto> registFeed(@ModelAttribute FeedRegisterDto feedRegisterDto) {
        return (ResponseEntity<FeedDto>) ObjectResult.build("result", feedService.saveFeed(feedRegisterDto));
    }

    @Operation(summary = "피드 조회")
    @GetMapping(value = "")
    public ResponseEntity<Page<FeedDto>> getFeed(@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        return (ResponseEntity<Page<FeedDto>>) PageResult.build(feedService.getFeed(page));
    }

    @Operation(summary = "피드 상세 조회")
    @GetMapping(value = "/{feedSeq}")
    public ResponseEntity<FeedDto> getFeed(@PathVariable(name = "feedSeq") Long feedSeq) {
        return (ResponseEntity<FeedDto>) ObjectResult.build("result", feedService.getFeedByFeedSeq(feedSeq));
    }

    @Operation(summary = "특정 유저 피드 조회")
    @GetMapping(value = "/user/{userSeq}")
    public ResponseEntity<Page<FeedDto>> getFeedByUserSeq(@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page, @PathVariable(name = "userSeq") Long userSeq) {
        return (ResponseEntity<Page<FeedDto>>) PageResult.build(feedService.getFeedByUserSeq(userSeq, page));
    }

}
