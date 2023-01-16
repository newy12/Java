package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.RefreshToken;
import com.summar.summar.domain.User;
import com.summar.summar.dto.*;
import com.summar.summar.repository.RefreshTokenRepository;
import com.summar.summar.repository.UserRepository;
import com.summar.summar.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final S3Service s3Service;

    @Transactional(readOnly = true)
    public Boolean checkNicknameDuplication(String nickname) throws NoSuchAlgorithmException {
        return userRepository.existsByUserNickname(nickname);
    }

    @Transactional
    public void changeUserInfo(ChangeUserInfoRequestDto changeUserInfoRequestDto) {
        User user = userRepository.findByUserNickname(changeUserInfoRequestDto.getUserNickname()).orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        //s3 저장
        ChangeUserInfoResponseDto changeUserInfoResponseDto = new ChangeUserInfoResponseDto();
        if(changeUserInfoRequestDto.getFile() != null){
            String profileImageUrl = s3Service.upload(changeUserInfoRequestDto.getFile(),"profile");
            changeUserInfoResponseDto.setProfileImageUrl(profileImageUrl.substring(8));
            changeUserInfoResponseDto.setUpdateUserNickname(changeUserInfoRequestDto.getUpdateUserNickname());
            changeUserInfoResponseDto.setMajor1(changeUserInfoRequestDto.getMajor1());
            changeUserInfoResponseDto.setMajor2(changeUserInfoRequestDto.getMajor2());
            user.changeUserInfo(changeUserInfoResponseDto);
            userRepository.save(user);
        }else{
            changeUserInfoResponseDto.setUpdateUserNickname(changeUserInfoRequestDto.getUpdateUserNickname());
            changeUserInfoResponseDto.setMajor1(changeUserInfoRequestDto.getMajor1());
            changeUserInfoResponseDto.setMajor2(changeUserInfoRequestDto.getMajor2());
            user.changeUserInfo(changeUserInfoResponseDto);
            userRepository.save(user);
        }
    }

    @Transactional
    public void addIntroduce(AddIntroduceRequestDto addIntroduceRequestDto) {
        User user = userRepository.findByUserEmail(addIntroduceRequestDto.getUserEmail()).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        user.setIntroduce(addIntroduceRequestDto.getIntroduce());
        userRepository.save(user);
    }

    @Transactional
    public String giveAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        User user = userRepository.findByUserEmail(refreshTokenRequestDto.getUserEmail()).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        if (jwtUtil.validateRefreshToken(refreshToken.getRefreshToken(), refreshTokenRequestDto.getUserEmail())) {
            return jwtUtil.generateToken(refreshTokenRequestDto.getUserEmail());
        }
        return null;
    }

    @Transactional
    public BothTokenResponseDto giveBothToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String accessToken = jwtUtil.generateToken(refreshTokenRequestDto.getUserEmail());
        String refreshToken = jwtUtil.generateRefreshToken(refreshTokenRequestDto.getUserEmail());
        User user = userRepository.findByUserEmail(refreshTokenRequestDto.getUserEmail()).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(user).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        refreshTokenInfo.setRefreshToken(user, refreshToken);
        UUID refreshTokenSeq = refreshTokenRepository.save(refreshTokenInfo).getRefreshTokenSeq();
        log.info(">>>>> : {}", refreshTokenInfo.getRefreshTokenSeq());
        return new BothTokenResponseDto(accessToken,refreshTokenSeq);
    }

    @Transactional(readOnly = true)
    public FindUserInfoResponseDto getUserInfo(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        return new FindUserInfoResponseDto(user.getUserNickname(),user.getMajor1(),user.getMajor2(),user.getIntroduce(),user.getFollower(),user.getFollowing(),user.getProfileImageUrl());
    }

    @Transactional
    public TokenResponseDto loginFlow(LoginRequestDto loginRequestDto) throws Exception {
        //access token 생성
        final String accessToken = jwtUtil.generateToken(loginRequestDto.getUserEmail());
        //refresh token 생성
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequestDto.getUserEmail());

        //nickname 또는 major 1 또는 major 2 가 비어있으면 회원가입
        if ("".equals(loginRequestDto.getUserNickname()) && "".equals(loginRequestDto.getMajor1()) && "".equals(loginRequestDto.getMajor2())
                && !userRepository.existsByUserEmail(loginRequestDto.getUserEmail())) {
            return new TokenResponseDto("발급x",UUID.randomUUID(),LoginStatus.회원가입,"","","",0,0);
        }
        //기존 회원이 있다면
        if (userRepository.existsByUserEmail(loginRequestDto.getUserEmail())) {
            User userInfo = userRepository.findByUserEmail(loginRequestDto.getUserEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + loginRequestDto.getUserEmail()));
            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo).orElseThrow(() ->
                    new SummarCommonException(SummarErrorCode.WRONG_TOKEN.getCode(), SummarErrorCode.WRONG_TOKEN.getMessage()));
            refreshTokenInfo.setRefreshToken(userInfo, refreshToken);
            UUID refreshTokenSeq = refreshTokenRepository.save(refreshTokenInfo).getRefreshTokenSeq();
            log.info(">>>>> : {}", refreshTokenSeq);

            //로그인 이력 업데이트
            userInfo.setLastLoginDateAndDeviceToken(LocalDate.now(),loginRequestDto.getDeviceToken());

            userRepository.save(userInfo);

            return new TokenResponseDto(accessToken,refreshTokenSeq,LoginStatus.로그인,userInfo.getUserNickname(),userInfo.getMajor1(),userInfo.getMajor2(),userInfo.getFollower(),userInfo.getFollowing());
        }
        //신규 회원이라면
        userRepository.save(new User(new UserSaveDto(loginRequestDto.getUserEmail(),loginRequestDto.getUserNickname(),loginRequestDto.getMajor1(),loginRequestDto.getMajor2(),0,0,loginRequestDto.getSocialType(),LocalDate.now(),loginRequestDto.getDeviceToken(),true)));
        User user = userRepository.findByUserEmail(loginRequestDto.getUserEmail()).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        RefreshToken refreshTokenInfo = new RefreshToken();
        refreshTokenInfo.setRefreshToken(user, refreshToken);
        UUID refreshTokenSeq = refreshTokenRepository.save(refreshTokenInfo).getRefreshTokenSeq();
        log.info(">>>>> : {}", refreshTokenSeq);
        return new TokenResponseDto(accessToken,refreshTokenSeq,LoginStatus.회원가입완료,"","","",0,0);
    }

    @Transactional(readOnly = true)
    public SearchUserInfoResponseDto searchUserInfo(String userNickname) {
        User user = userRepository.findByUserNickname(userNickname).orElseThrow(() ->
                new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        return new SearchUserInfoResponseDto(user.getUserEmail(),user.getFollower(),user.getFollowing(),user.getUserNickname(),user.getMajor1(),user.getMajor2());
    }

    @Transactional(readOnly = true)
    public Page<SearchUserListResponseDto> searchUserList(String userNickname, Pageable pageable) {
        //닉네임 검색 완성했을 때
        boolean searchUserListCheck = userRepository.existsByUserNicknameContains(userNickname);
        if(searchUserListCheck){
            Page<User> searchUserList = userRepository.findByUserNicknameContains(userNickname,pageable);
            return searchUserList.map(SearchUserListResponseDto::new);
        }
        List<String> index_list = new ArrayList<>();
        index_list.add("ㄱ");
        index_list.add("ㄴ");
        index_list.add("ㄷ");
        index_list.add("ㄹ");
        index_list.add("ㅁ");
        index_list.add("ㅂ");
        index_list.add("ㅅ");
        index_list.add("ㅇ");
        index_list.add("ㅈ");
        index_list.add("ㅊ");
        index_list.add("ㅋ");
        index_list.add("ㅌ");
        index_list.add("ㅍ");
        index_list.add("ㅎ");
        Map<Integer, String> index_map = new HashMap<>();
        index_map.put(0, "가");
        index_map.put(1, "나");
        index_map.put(2, "다");
        index_map.put(3, "라");
        index_map.put(4, "마");
        index_map.put(5, "바");
        index_map.put(6, "사");
        index_map.put(7, "아");
        index_map.put(8, "자");
        index_map.put(9, "차");
        index_map.put(10, "카");
        index_map.put(11, "타");
        index_map.put(12, "파");
        index_map.put(13, "하");
        index_map.put(14, "힣");
        //닉네임 검색 초성일 때
        ;
        int num = 0;
        for (int i = 0; i < index_list.size(); i++) {

            if (userNickname.equals(index_list.get(i))) {
                num = i;
                break;
            }
        }
        Page<User> users = userRepository.searchWord(index_map.get(num), index_map.get(num + 1),pageable);
        return users.map(SearchUserListResponseDto::new);
    }

    @Transactional
    public void changePushNotification(PushNotificationStatusDto pushNotificationStatusDto) {
        User user = userRepository.findByUserNickname(pushNotificationStatusDto.getUserNickname())
                .orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        user.setPushAlarmYn(pushNotificationStatusDto.getStatus());
        userRepository.save(user);
    }

    public UserPushStatusInfoResponseDto userPushStatusInfo(String userNickname) {
        User user = userRepository.findByUserNickname(userNickname)
                .orElseThrow(() -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
        UserPushStatusInfoResponseDto userPushStatusInfoResponseDto = new UserPushStatusInfoResponseDto();
        userPushStatusInfoResponseDto.setStatus(user.getPushAlarmYn());
        return userPushStatusInfoResponseDto;
    }
}
