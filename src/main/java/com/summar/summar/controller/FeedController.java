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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<?> registFeed(@ModelAttribute FeedRegisterDto feedRegisterDto) {
        return ObjectResult.build("result", feedService.saveFeed(feedRegisterDto));
    }

    @Operation(summary = "피드 조회")
    @GetMapping(value = "")
    public ResponseEntity<Page<FeedDto>> getFeed(@PageableDefault(size = 20,sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.ok().body(feedService.getFeed(page));
    }

    @Operation(summary = "특정 유저 피드 조회")
    @GetMapping(value = "/{userSeq}")
    public ResponseEntity<Page<FeedDto>> getFeedByUserSeq(@PageableDefault(size = 20,sort = "createdDate", direction = Sort.Direction.DESC) Pageable page, @PathVariable(name="userSeq") Long userSeq) {
        return ResponseEntity.ok().body(feedService.getFeedByUserSeq(userSeq,page));
    }

}
