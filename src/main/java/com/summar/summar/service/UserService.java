package com.summar.summar.service;

import com.summar.summar.common.SummarCommonException;
import com.summar.summar.common.SummarErrorCode;
import com.summar.summar.domain.User;
import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.repository.UserRepository;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.util.SHA256Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Boolean saveUser(JoinRequestDto joinRequestDto) throws NoSuchAlgorithmException {
        List<User> userInfoList = userRepository.findAll();
        if (ObjectUtils.isEmpty(userInfoList)){
            return false;
        }
        for (User user : userInfoList) {
            if(user.getUserId().equals(joinRequestDto.getUserId()) || user.getUserNickname().equals(joinRequestDto.getUserNickname())){
                log.info("아이디 혹은 닉네임이 중복 됩니다.");
                return false;
            }
        }
        //passwordEncoder 양방향 암호화 알고리즘 적용
        joinRequestDto.setUserHpNo(passwordEncoder.encode(joinRequestDto.getUserHpNo()));
        //SHA256 단방향 암호화 알고리즘 적용
        joinRequestDto.setUserPwd(SHA256Util.encrypt(joinRequestDto.getUserPwd()));
        userRepository.save(new User(joinRequestDto));
        return true;
    }
}
