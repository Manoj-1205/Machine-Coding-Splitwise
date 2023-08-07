package com.spring.splitwise.dtos;

import com.spring.splitwise.models.Group;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGroupRequest {
    private Long userId;
    private String name;


}
