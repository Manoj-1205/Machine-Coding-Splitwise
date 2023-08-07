package com.spring.splitwise.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="expense_groups")
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User createdBy;

    @ManyToMany
    private List<User> members=new ArrayList<>();

    @ManyToMany
    private List<User> admins=new ArrayList<>();


}
