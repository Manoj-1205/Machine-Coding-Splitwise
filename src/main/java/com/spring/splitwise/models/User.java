package com.spring.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@Data
@Builder
@Table(name="users")
public class User extends BaseModel{
    private String name;
    private String emailId;
    private String password;
    private String phoneNumber;

}
