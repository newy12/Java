package com.summar.summar.service;

import com.summar.summar.domain.User;
import com.summar.summar.dto.ChangeUserInfoRequestDto;
import com.summar.summar.dto.ChangeUserInfoResponseDto;
import com.summar.summar.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    static class TestUser extends User {
        public TestUser(){
            super();
        }
    }


    @Test
    @DisplayName("유저 정보가 제대로 바뀌는 지 확인")
    void changeUserInfo(){
        //given
        User user = new TestUser();
        ReflectionTestUtils.setField(user,"userNickname","beforeNickname");
        ReflectionTestUtils.setField(user,"major1","beforemajor1");
        ReflectionTestUtils.setField(user,"major2","beforemajor2");
        ReflectionTestUtils.setField(user,"profileImageUrl","beforeImageUrl");
        ReflectionTestUtils.setField(user,"introduce","beforeIntroduce");
        ReflectionTestUtils.setField(user,"leaveYn",false);

        given(userRepository.findByUserNicknameAndLeaveYn(any(),any())).willReturn(Optional.of(user));

        ChangeUserInfoRequestDto changeUserInfoRequestDto = ChangeUserInfoRequestDto.builder()
                .updateUserNickname("afterNickname")
                .major1("afterMajor1")
                .major2("afterMajor2")
                .introduce("afterIntroduce")
                .build();
        when(userRepository.save(any(User.class))).then(AdditionalAnswers.delegatesTo(user));
        //when
        userService.changeUserInfo(changeUserInfoRequestDto);

        //then
        assertEquals("afterNickname",user.getUserNickname());
    }
}
