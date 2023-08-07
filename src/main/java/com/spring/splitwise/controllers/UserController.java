package com.spring.splitwise.controllers;

import com.spring.splitwise.Service.UserService;
import com.spring.splitwise.dtos.CreateUserRequest;
import com.spring.splitwise.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;
    public User createUser(CreateUserRequest request){
        return userService.createUser(request.toUser());
    }
}
