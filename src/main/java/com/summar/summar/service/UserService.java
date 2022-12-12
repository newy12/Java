package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.User;
import com.summar.summar.dto.LoginCheckRequestDto;
import com.summar.summar.dto.LoginRequestDto;
import com.summar.summar.dto.MajorResponseDto;
import com.summar.summar.dto.UserSaveDto;
import com.summar.summar.repository.MajorRepository;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final MajorRepository majorRepository;

    @Transactional
    public User saveUser(LoginRequestDto loginRequestDto) {
        return userRepository.save(
                new User(UserSaveDto.builder()
                        .userEmail(loginRequestDto.getUserEmail())
                        .userNickname(loginRequestDto.getUserNickname())
                        .major1(loginRequestDto.getMajor1())
                        .major2(loginRequestDto.getMajor2())
                        .socialType(loginRequestDto.getSocialType())
                        .lastLoginDate(LocalDate.now())
                .build())
        );
    }

    @Transactional(readOnly = true)
    public Boolean checkNicknameDuplication(String nickname) throws NoSuchAlgorithmException {
        return userRepository.existsByUserNickname(nickname);
    }

    @Transactional(readOnly = true)
    public Boolean checkUserIdDuplication(String userId) throws NoSuchAlgorithmException {
        return userRepository.existsByUserEmail(userId);
    }

    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userRepository.findByUserEmail(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));
    }

    @Transactional
    public void updateLastUserLoginDate(User userInfo) {
        userInfo.setLastLoginDate(LocalDate.now());
        userRepository.save(userInfo);
    }

    /*@Transactional(readOnly = true)
    public Boolean userHpNoDuplication(SmsRequestDto smsRequestDto) throws Exception {
        List<User> userList = userRepository.findAll();

        log.info("userList : {}",userList);

        if(!ObjectUtils.isEmpty(userList)){
            for (User userInfo : userList) {
                //휴대번호 중복 존재 = true
                if(AES256Cipher.decrypt(userInfo.getUserHpNo()).equals(smsRequestDto.getUserHpNo())){
                    return true;
                }
                //휴대번호 중복 없음 = false;
                return false;
            }
        }
        throw new NullPointerException();
    }*/
/*
    @Transactional(readOnly = true)
    public User getUserInfo(String userHpNo) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return userRepository.findByUserHpNo(AES256Cipher.encrypt(userHpNo)).orElseThrow(
                () ->new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
    }*/

    @Transactional
    public boolean checkUserEmail(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }
    @Transactional
    public boolean checkUserEmailAndSocialType(LoginCheckRequestDto loginCheckRequestDto) {
        return userRepository.existsByUserEmailAndSocialType(loginCheckRequestDto.getUserEmail(), loginCheckRequestDto.getSocialType());
    }

    public User findUserInfo(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new SummarCommonException(SummarErrorCode.USER_NOT_FOUND.getCode(), SummarErrorCode.USER_NOT_FOUND.getMessage()));
    }

    public List<MajorResponseDto> findChildMajorByParentsSeq(Long majorSeq) {
        List<MajorResponseDto> majorResponseDtoList = new ArrayList<>();
        majorRepository.findAllByParentsSeq(majorSeq).forEach(
                major -> majorResponseDtoList.add(new MajorResponseDto(major)));

        return majorResponseDtoList;
    }

}
