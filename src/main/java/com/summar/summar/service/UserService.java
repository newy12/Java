package com.summar.summar.service;

import com.summar.summar.domain.User;
import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.dto.SmsRequestDto;
import com.summar.summar.repository.UserRepository;
import com.summar.summar.util.AES256Cipher;
import com.summar.summar.util.SHA256Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public Boolean saveUser(JoinRequestDto joinRequestDto) throws Exception {
        //AES-128 양방향 암호화 알고리즘 적용
        joinRequestDto.setUserHpNo(AES256Cipher.encrypt(joinRequestDto.getUserHpNo()));
        //SHA-256 단방향 암호화 알고리즘 적용
        joinRequestDto.setUserPwd(SHA256Util.encrypt(joinRequestDto.getUserPwd()));
        userRepository.save(new User(joinRequestDto));
        return true;
    }

    @Transactional(readOnly = true)
    public Boolean checkNicknameDuplication(String nickname) throws NoSuchAlgorithmException {
        return userRepository.existsByUserNickname(nickname);
    }

    @Transactional(readOnly = true)
    public Boolean checkUserIdDuplication(String userId) throws NoSuchAlgorithmException {
        return userRepository.existsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));
    }

    @Transactional
    public void updateLastUserLoginDate(User userInfo) {
        userInfo.setLastLoginDate(LocalDate.now());
        userRepository.save(userInfo);
    }

    @Transactional(readOnly = true)
    public Boolean userHpNoDuplication(SmsRequestDto smsRequestDto) throws Exception {
        List<User> userList = userRepository.findAll();
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
    }
}
