package com.summar.summar.service;

import com.summar.summar.domain.User;
import com.summar.summar.dto.JoinRequestDto;
import com.summar.summar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
}
