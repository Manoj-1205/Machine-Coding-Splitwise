package com.spring.splitwise.Service;

import com.spring.splitwise.Repositories.UserExpenseRepository;
import com.spring.splitwise.models.User;
import com.spring.splitwise.models.UserExpense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserExpenseService {
    private UserExpenseRepository userExpenseRepository;
    public UserExpense saveUserExpense(UserExpense userExpense){
        return userExpenseRepository.save(userExpense);
    }
}
