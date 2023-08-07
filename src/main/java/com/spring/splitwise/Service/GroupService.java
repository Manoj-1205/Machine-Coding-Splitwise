package com.spring.splitwise.Service;

import com.spring.splitwise.Repositories.GroupRepository;
import com.spring.splitwise.dtos.CreateGroupRequest;
import com.spring.splitwise.models.Group;
import com.spring.splitwise.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                .build();
//        group.getAdmins().add(user);
        return groupRepository.save(group);
    }
}
