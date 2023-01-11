package com.summar.summar.controller;

import com.summar.summar.dto.FollowerRequestDto;
import com.summar.summar.dto.FollowerSaveDto;
import com.summar.summar.results.ListResult;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.results.PageResult;
import com.summar.summar.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
public class FollowController {

    private final FollowService followService;


    /**
     * 팔로워 전체조회
     * @param userNickname
     * @param pageable
     * @return
     */
    @Operation(summary = "팔로워 전체조회", description = "팔로워 전체조회를 합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"firstPage\": true,\n" +
                    "    \"lastPage\": true,\n" +
                    "    \"totalPageCount\": 1,\n" +
                    "    \"recordsPerPage\": 20,\n" +
                    "    \"content\": [\n" +
                    "        {\n" +
                    "            \"userNickname\": \"영재2\",\n" +
                    "            \"introduce\": null,\n" +
                    "            \"major1\": \"공학계열\",\n" +
                    "            \"major2\": \"컴퓨터정보공학과\",\n" +
                    "            \"follower\": 1,\n" +
                    "            \"following\": 1\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"totalRecordCount\": 1,\n" +
                    "    \"currentPageNo\": 1\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/followers")
    public ResponseEntity<?> findFollowers(@RequestParam(value = "userNickname")String userNickname, Pageable pageable){
        return PageResult.build(followService.findFollowers(userNickname,pageable));
    }

    /**
     * 팔로윙 전체조회
     * @param userNickname
     * @param pageable
     * @return
     */
    @Operation(summary = "팔로윙 전체조회", description = "팔로윙 전체조회를 합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"firstPage\": true,\n" +
                    "    \"lastPage\": true,\n" +
                    "    \"totalPageCount\": 1,\n" +
                    "    \"recordsPerPage\": 20,\n" +
                    "    \"content\": [\n" +
                    "        {\n" +
                    "            \"userNickname\": \"영재2\",\n" +
                    "            \"introduce\": null,\n" +
                    "            \"major1\": \"공학계열\",\n" +
                    "            \"major2\": \"컴퓨터정보공학과\",\n" +
                    "            \"follower\": 1,\n" +
                    "            \"following\": 1\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"totalRecordCount\": 1,\n" +
                    "    \"currentPageNo\": 1\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/followings")
    public ResponseEntity<?> findFollowings(@RequestParam(value = "userNickname")String userNickname, Pageable pageable){
        return PageResult.build(followService.findFollowings(userNickname,pageable));
    }


    /**
     * 팔로우 추가
     * @param followerRequestDto
     * @return
     */
    @Operation(summary = "팔로우 추가", description = "팔로우 추가를 합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"message\": \"정상처리\",\n" +
                    "    \"errorMessage\": \"\",\n" +
                    "    \"errorCode\": \"\",\n" +
                    "    \"result\": null\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @PostMapping("/follower")
    public ResponseEntity<?> addFollower(@RequestBody FollowerRequestDto followerRequestDto){
        followService.addFollower(followerRequestDto);
        return ObjectResult.ok();
    }
    /**
     * 팔로우 취소
     * @param followerRequestDto
     * @return
     */
    @Operation(summary = "팔로우 취소", description = "팔로우를 취소를 합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = "{\n" +
                    "    \"status\": \"SUCCESS\",\n" +
                    "    \"message\": \"정상처리\",\n" +
                    "    \"errorMessage\": \"\",\n" +
                    "    \"errorCode\": \"\",\n" +
                    "    \"result\": null\n" +
                    "}"))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @DeleteMapping("/follower")
    public ResponseEntity<?> deleteFollower(@RequestBody FollowerRequestDto followerRequestDto){
        followService.deleteFollower(followerRequestDto);
        return ObjectResult.ok();
    }
}
