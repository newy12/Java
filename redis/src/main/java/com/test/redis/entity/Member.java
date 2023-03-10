package com.test.redis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Entity
@Getter
//@RedisHash(value = "memberInfo",timeToLive = 10)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    private String sex;
    private String height;
    private String weight;

    public void updateRedis(String name) {
        this.name = name;
    }
}
