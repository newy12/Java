package com.summar.member.service;

import com.summar.member.auth.LoginUser;
import com.summar.member.auth.SummarUser;
import com.summar.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser loginUser = userRepository.findByUserId(username).map(LoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        if (loginUser != null) {
            return new SummarUser(loginUser);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}