package com.spring.splitwise.dtos;

import com.spring.splitwise.models.Group;
import com.spring.splitwise.models.User;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
public class CreateMembersRequest {
    private Long userId;
    private Long groupId;
    private List<Long> members;
}
