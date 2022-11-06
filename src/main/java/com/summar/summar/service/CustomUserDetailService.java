package com.summar.summar.service;

import com.summar.summar.auth.LoginUser;
import com.summar.summar.auth.SummarUser;
import com.summar.summar.repository.UserRepository;
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

       /* LoginUser loginUser = userRepository.findByUserId(username).map(LoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        if(loginUser != null) {
            return new SummarUser(loginUser);
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }*/
        return null;
    }

}