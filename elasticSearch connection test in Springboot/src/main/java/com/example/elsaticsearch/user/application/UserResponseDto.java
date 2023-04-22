package com.example.elsaticsearch.user.application;

import com.example.elsaticsearch.user.domain.Users;

public class UserResponseDto {
    private Long id;
    private String name;
    private String description;

    private UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static UserResponseDto from(Users users) {
        return new UserResponseDto(users.getId(), users.getName(), users.getDescription());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
