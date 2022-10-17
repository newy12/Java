package com.summar.summar.service;

import com.summar.summar.domain.User;
import com.summar.summar.dto.ApiResponseMessage;
import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.repository.UserRepository;
import com.summar.summar.results.ApiResult;
import com.summar.summar.results.BooleanResult;
import com.summar.summar.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(JoinRequestDto joinRequestDto) {
        userRepository.save(new User(joinRequestDto));
    }

    public ApiResult validation(JoinRequestDto joinRequestDto) {
        if(joinRequestDto.getUserId() == ""){
            return ApiResult.message("아이디를 입력해주세요");
        }
        return null;
    }
}
