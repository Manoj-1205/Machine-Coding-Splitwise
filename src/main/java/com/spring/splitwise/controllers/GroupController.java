package com.spring.splitwise.controllers;

import com.spring.splitwise.Service.GroupService;
import com.spring.splitwise.dtos.CreateGroupRequest;
import com.spring.splitwise.dtos.CreateMembersRequest;
import com.spring.splitwise.models.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class GroupController {
    GroupService groupService;
    public Group createGroup(CreateGroupRequest request){
        return groupService.createGroup(request);
    }

    public Group addMembers(CreateMembersRequest request){
        return groupService.addMembers(request);
    }
}
