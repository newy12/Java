package com.summar.summar.controller;

import com.summar.summar.dto.FollowerRequestDto;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.results.PageResult;
import com.summar.summar.service.FollowService;
import com.summar.summar.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
     * @param userSeq
     * @param pageable
     * @return
     */
    @Operation(summary = "팔로워 전체조회", description = "팔로워 전체조회를 합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.findFollowers))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/followers")
    public ResponseEntity<?> findFollowers(@RequestParam(value = "userSeq")Long userSeq, Pageable pageable){
        return PageResult.build(followService.findFollowers(userSeq,pageable));
    }

    /**
     * 팔로윙 전체조회
     * @param userSeq
     * @param pageable
     * @return
     */
    @Operation(summary = "팔로윙 전체조회", description = "팔로윙 전체조회를 합니다.", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.findFollowings))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/followings")
    public ResponseEntity<?> findFollowings(@RequestParam(value = "userSeq")Long userSeq, Pageable pageable){
        return PageResult.build(followService.findFollowings(userSeq,pageable));
    }


    /**
     * 팔로우 추가
     * @param followerRequestDto
     * @return
     */
    @Operation(summary = "팔로우 추가", description = "팔로우 추가를 합니다. 'followedUserSeq = 팔로우 당한 사람', 'followingUserSeq = 팔로우 건 사람'", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.addFollower))),
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
    @Operation(summary = "팔로우 취소", description = "팔로우를 취소를 합니다. 'followedUserSeq = 팔로우 당한 사람', 'followingUserSeq = 팔로우 건 사람'", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.deleteFollower))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @DeleteMapping("/follower")
    public ResponseEntity<?> deleteFollower(@RequestBody FollowerRequestDto followerRequestDto){
        followService.deleteFollower(followerRequestDto);
        return ObjectResult.ok();
    }

    /**
     * 팔로우 체크
     * @param followedUserSeq
     * @param followingUserSeq
     * @return
     */
    @Operation(summary = "팔로우 했는지 확인", description = "팔로우 했는지 확인합니다. " +
            "\"true 일경우에 팔로우됀 상태\" " +
            "\"false 일경우에 팔로우 안됀 상태\"", security = @SecurityRequirement(name = "Authorization"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상 처리", content = @Content(examples = @ExampleObject(value = StringUtil.followCheck))),
            @ApiResponse(responseCode = "403", description = "권한 없음(다른 회원의 계정 변경)", content = @Content(examples = @ExampleObject(value = "\"result\":null"))),
    })
    @GetMapping("/follow-check")
    public ResponseEntity<?> followCheck(@Parameter(description = "팔로우 당한 사람",required = true) @RequestParam(value = "followedUserSeq")Long followedUserSeq,
                                         @Parameter(description = "팔로우 건사람")@RequestParam(value = "followingUserSeq")Long followingUserSeq){
        return BooleanResult.build("result",followService.followCheck(followedUserSeq,followingUserSeq));
    }
}
