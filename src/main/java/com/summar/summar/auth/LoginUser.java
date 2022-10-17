package com.summar.summar.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.summar.summar.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class LoginUser implements Serializable {

    private Long userSeq;
    private String userId;
    private String userPwd;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean lockedYn;
    private User user;
    @JsonIgnore
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
    public LoginUser() {
        this.authorities = new ArrayList<>();
    }

    public LoginUser(User entity){
        this.userSeq = entity.getUserSeq();
        this.userId = entity.getUserId();
        this.userPwd = entity.getUserPwd();
        this.lockedYn = true;
        this.authorities = new ArrayList<GrantedAuthority>(){{
            add(new SimpleGrantedAuthority("test"));
        }};
        this.user = entity;
    }
}
