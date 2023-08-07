package com.spring.splitwise.Service;

import com.spring.splitwise.Repositories.GroupRepository;
import com.spring.splitwise.dtos.CreateGroupRequest;
import com.spring.splitwise.dtos.CreateMembersRequest;
import com.spring.splitwise.models.Group;
import com.spring.splitwise.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class GroupService {
    GroupRepository groupRepository;
    UserService userService;
    public Group createGroup(CreateGroupRequest request) {
        User user = userService.getUser(request.getUserId());
        if(user==null){
            throw new NoSuchElementException("User Not Found");
        }
        Group group = Group.builder()
                .name(request.getName())
                .createdBy(user)
                .admins(new ArrayList<>())
                .members(new ArrayList<>())
                .build();
        group.getAdmins().add(user);
        group.getMembers().add(user);
        return groupRepository.save(group);
    }

    public Group addMembers(CreateMembersRequest request) {
        User user = userService.getUser(request.getUserId());
        if(user==null){
            throw new NoSuchElementException("User Not Found");
        }
        System.out.println("GROUP ID "+request.getGroupId());
        Group group = getGroup(request.getGroupId());
        if(!group.getAdmins().contains(user)){
            return null;
        }
        for(Long userId : request.getMembers()){
            User member = userService.getUser(userId);

            if(member!=null){
                group.getMembers().add(member);
            }
        }
        return groupRepository.save(group);
    }

    public Group getGroup(Long id){
        return groupRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Group Not found"));
    }


}
