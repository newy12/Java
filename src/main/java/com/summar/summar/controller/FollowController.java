package com.summar.summar.controller;

import com.summar.summar.dto.FollowerRequestDto;
import com.summar.summar.dto.FollowerSaveDto;
import com.summar.summar.results.ListResult;
import com.summar.summar.results.ObjectResult;
import com.summar.summar.results.PageResult;
import com.summar.summar.service.FollowService;
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
     * 팔로워 리스트 조회
     * @param userNickname
     * @param pageable
     * @return
     */
    @GetMapping("/followers")
    public ResponseEntity<?> findFollowers(@RequestParam(value = "userNickname")String userNickname, Pageable pageable){
        return PageResult.build(followService.findFollowers(userNickname,pageable));
    }

   /* @GetMapping("/followings")
    public ResponseEntity<?> findFollowings(@RequestParam(value = "userNickname")String userNickname, Pageable pageable){
        return PageResult.build(followService.findFollowings(userNickname,pageable));
    }*/

    /**
     * 팔로우 추가
     * @param followerRequestDto
     * @return
     */
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
    @DeleteMapping("/follower")
    public ResponseEntity<?> deleteFollower(@RequestBody FollowerRequestDto followerRequestDto){
        followService.deleteFollower(followerRequestDto);
        return ObjectResult.ok();
    }
}
