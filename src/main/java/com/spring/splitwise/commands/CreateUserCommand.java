package com.spring.splitwise.commands;

import com.spring.splitwise.controllers.UserController;
import com.spring.splitwise.dtos.CreateUserRequest;
import com.spring.splitwise.models.User;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
//@NoArgsConstructor
public class CreateUserCommand implements Command{


    private UserController userController;
    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        String command = tokens.get(0);
        if(!command.equals(Commands.REGISTER_USER_COMMAND)){
            return false;
        }
        //Other Validations
        if(tokens.size() < 5){
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input) {
//        System.out.println("Executing CREATE USER command..");
        //Call a user controller

        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        CreateUserRequest request = CreateUserRequest.builder()
                .name(tokens.get(1))
                .email(tokens.get(2))
                .password(tokens.get(3))
                .phoneNumber(tokens.get(4))
                .build();

        User user = userController.createUser(request);
        System.out.println("Created user with id "+user.getId());

    }
}
