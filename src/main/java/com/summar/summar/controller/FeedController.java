package com.summar.summar.controller;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.dto.*;
import com.summar.summar.results.BooleanResult;
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

import javax.servlet.http.HttpServletRequest;

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
        if (feedRegisterDto.getImages().get(0).getSize() < 1 && feedRegisterDto.getContents().length() < 1) {
            throw new SummarCommonException(SummarErrorCode.INVALID_TEMP_SAVE.getCode(), SummarErrorCode.INVALID_TEMP_SAVE.getMessage());
        }
        return (ResponseEntity<FeedDto>) ObjectResult.build("result", feedService.saveFeed(feedRegisterDto));
    }

    @Operation(summary = "피드 조회")
    @GetMapping(value = "")
    public ResponseEntity<Page<FeedDto>> getFeed(@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        return (ResponseEntity<Page<FeedDto>>) PageResult.build(feedService.getFeed(page));
    }

    @Operation(summary = "임시 저장 피드 조회")
    @GetMapping(value = "/temp/{userSeq}")
    public ResponseEntity<Page<FeedDto>> getTempFeed(@PathVariable(name="userSeq") Long userSeq,@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        return (ResponseEntity<Page<FeedDto>>) PageResult.build(feedService.getTempFeed(userSeq,page));
    }

    @Operation(summary = "피드 상세 조회")
    @GetMapping(value = "/{feedSeq}")
    public ResponseEntity<FeedDto> getFeed(@PathVariable(name = "feedSeq") Long feedSeq) {
        return (ResponseEntity<FeedDto>) ObjectResult.build("result", feedService.getFeedByFeedSeq(feedSeq));
    }

    @Operation(summary = "피드 비활성화 (삭제)")
    @PatchMapping(value = "/{feedSeq}")
    public ResponseEntity<FeedDto> setFeedInActivated(@PathVariable(name = "feedSeq") Long feedSeq) {
        return (ResponseEntity<FeedDto>) ObjectResult.build("result", feedService.updateFeedInActivated(feedSeq));
    }

    @Operation(summary = "특정 유저 피드 조회")
    @GetMapping(value = "/user/{userSeq}")
    public ResponseEntity<Page<FeedDto>> getFeedByUserSeq(@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page, @PathVariable(name = "userSeq") Long userSeq) {
        return (ResponseEntity<Page<FeedDto>>) PageResult.build(feedService.getFeedByUserSeq(userSeq, page));
    }

    @Operation(summary = "피드 좋아요 추가/삭제")
    @PostMapping(value = "/like/{feedSeq}")
    public ResponseEntity<BooleanResult> setFeedLike(@PathVariable(name = "feedSeq") Long feedSeq, @RequestBody FeedLikeDto feedLikeDto) {
        return BooleanResult.build("result", feedService.setFeedLike(feedSeq, feedLikeDto));
    }

    @Operation(summary = "특정 피드 댓글 조회")
    @GetMapping(value = "{feedSeq}/comments")
    public ResponseEntity<Page<FeedCommentDto>> getFeedCommentsByFeedSeq(@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page,
                                                                @PathVariable(name = "feedSeq")  Long feedSeq) {
        return (ResponseEntity<Page<FeedCommentDto>>) PageResult.build(feedService.getFeedCommentsByFeedSeq(page, feedSeq));
    }

    @Operation(summary = "댓글 등록")
    @PostMapping(value = "{feedSeq}/comments")
    public ResponseEntity<?> registFeedComment(@RequestBody FeedCommentRegisterDto feedCommentRegisterDto) {
        feedService.saveFeedComment(feedCommentRegisterDto);
        return ObjectResult.ok();
    }

    @Operation(summary = "댓글 비활성화 (삭제)")
    @PatchMapping(value = "/comments/{feedCommentSeq}")
    public ResponseEntity<?> setFeedCommentInActivated(@PathVariable(name = "feedCommentSeq") Long feedCommentSeq) {
        feedService.updateFeedCommentInActivated(feedCommentSeq);
        return ObjectResult.ok();
    }

    @Operation(summary = "댓글 수정")
    @PutMapping(value = "{feedSeq}/comments")
    public ResponseEntity<?> updateFeedComment(@RequestBody FeedCommentUpdateDto feedCommentUpdateDto) {
        feedService.updateFeedComment(feedCommentUpdateDto);
        return ObjectResult.ok();
    }

    @Operation(summary = "유저 피드 스크랩 조회")
    @GetMapping(value = "/scrap")
    public ResponseEntity<Page<FeedDto>> getFeedScrap(@PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        return (ResponseEntity<Page<FeedDto>>) PageResult.build(feedService.getFeedScrap(page));
    }

    @Operation(summary = "피드 스크랩 추가/삭제")
    @PostMapping(value = "/scrap/{feedSeq}")
    public ResponseEntity<BooleanResult> setFeedScrap(@PathVariable(name = "feedSeq") Long feedSeq, @RequestBody FeedScrapDto feedScrapDto) {
        return BooleanResult.build("result", feedService.setFeedScrap(feedSeq, feedScrapDto));
    }


}
