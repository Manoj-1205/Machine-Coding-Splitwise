package com.spring.splitwise.dtos;

import com.spring.splitwise.models.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    public User toUser(){
        return User.builder()
                .name(name)
                .emailId(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
