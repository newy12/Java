package com.summar.summar.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SummarUser extends User {

    public Long userSeq;

    public LoginUser loginUser;

    public SummarUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SummarUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    /*public SummarUser(LoginUser loginUser){
        super(loginUser.getUserEmail()
                , loginUser.getUserPwd()
                , true
                , true
                , true
                , loginUser.getLockedYn()
                , loginUser.getAuthorities()
        );

        this.userSeq = loginUser.getUserSeq();
        this.loginUser = loginUser;
    }*/
}
