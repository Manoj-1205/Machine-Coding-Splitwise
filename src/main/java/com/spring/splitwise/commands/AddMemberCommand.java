package com.spring.splitwise.commands;

import com.spring.splitwise.Service.GroupService;
import com.spring.splitwise.controllers.GroupController;
import com.spring.splitwise.dtos.CreateMembersRequest;
import com.spring.splitwise.models.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Command - u1 AddMember g1 u2
@Component
@AllArgsConstructor
public class AddMemberCommand implements Command{
    private GroupController groupController;
    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(!tokens.get(1).equals(Commands.ADD_MEMBER_COMMAND)){
            return false;
        }
        return tokens.get(0).startsWith("u") && tokens.get(2).startsWith("g") && tokens.get(3).startsWith("u");
    }

    @Override
    public void execute(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        CreateMembersRequest createMembersRequest = CreateMembersRequest.builder()
                .userId(getIdFromString(tokens.get(0)))
                .groupId(getIdFromString(tokens.get(2)))
                .members(new ArrayList<>())
                .build();
        for(int j=3;j<tokens.size();j++){
            createMembersRequest.getMembers().add(getIdFromString(tokens.get(j)));
        }
        Group group = groupController.addMembers(createMembersRequest);
        if(group==null){
            System.out.println("User is not an Admin");
            System.out.println("Members not added!!");
            return;
        }
        System.out.println("Members added to group id : "+group.getId());
    }

    Long getIdFromString(String str){
        return Long.parseLong(str.substring(1));
    }
}
