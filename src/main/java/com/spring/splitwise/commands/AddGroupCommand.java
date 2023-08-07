package com.spring.splitwise.commands;

import com.spring.splitwise.controllers.GroupController;
import com.spring.splitwise.dtos.CreateGroupRequest;
import com.spring.splitwise.models.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class AddGroupCommand implements Command{

    private GroupController groupController;
    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(!tokens.get(1).equals(Commands.ADD_GROUP_COMMAND)){
            return false;
        }
        return tokens.size()==3;
    }

    @Override
    public void execute(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        CreateGroupRequest request = CreateGroupRequest.builder()
                .userId(Long.parseLong(tokens.get(0)))
                .name(tokens.get(2))
                .build();
        Group group = groupController.createGroup(request);
        System.out.println("Created Group with Id : "+group.getId());
    }
}
