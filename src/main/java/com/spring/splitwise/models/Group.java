package com.spring.splitwise.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="expense_groups")
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User createdBy;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> members=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> admins=new ArrayList<>();


}
