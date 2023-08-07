package com.spring.splitwise.Service;

import com.spring.splitwise.Repositories.UserRepository;
import com.spring.splitwise.dtos.CreateUserRequest;
import com.spring.splitwise.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
