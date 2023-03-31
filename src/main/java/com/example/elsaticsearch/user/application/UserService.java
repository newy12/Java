package com.example.elsaticsearch.user.application;

import com.example.elsaticsearch.user.domain.UserRepository;
import com.example.elsaticsearch.user.domain.Users;
import com.example.elsaticsearch.user.domain.search.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSearchRepository userSearchRepository;

    @Transactional
    public Long save(UserRequestDto userRequestDto) {
        Users users = new Users(userRequestDto.getName(), userRequestDto.getDescription());
        Users savedUsers = userRepository.save(users);
        userSearchRepository.save(users);
        return savedUsers.getId();
    }

    public List<UserResponseDto> searchByName(String name, Pageable pageable) {
        // userSearchRepository.findByBasicProfile_NameContains(name) 가능
        return userSearchRepository.searchByName(name, pageable)
                .stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }
}
