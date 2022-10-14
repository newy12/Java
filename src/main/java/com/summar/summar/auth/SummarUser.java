package com.summar.summar.auth;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class SummarUser extends User {

    public Long userSeq;

    public LoginUser loginUser;

    public SummarUser(LoginUser loginUser){
        super(loginUser.getUserId()
                , loginUser.getUserPwd()
                , true
                , true
                , true
                , loginUser.getLockedYn()
                , loginUser.getAuthorities()
        );

        this.userSeq = loginUser.getUserSeq();
        this.loginUser = loginUser;
    }
}
