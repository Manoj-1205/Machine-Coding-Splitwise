package com.spring.splitwise.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class CreateGroupExpense {
    private Long addedBy;
    private Long groupId;
    private String paymentType;
    private Integer amount;
    private String splitType;
    private String description;

}
