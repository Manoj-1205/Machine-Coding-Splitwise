package com.spring.splitwise.Service;

import com.spring.splitwise.Repositories.ExpenseRepository;
import com.spring.splitwise.dtos.CreateGroupExpense;
import com.spring.splitwise.enums.ExpenseType;
import com.spring.splitwise.models.Expense;
import com.spring.splitwise.models.Group;
import com.spring.splitwise.models.User;
import com.spring.splitwise.models.UserExpense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

//Command : //u1 Expense g5 iPay 1000 Equal Desc Wifi Bill
@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private GroupService groupService;
    private UserService userService;

    private  UserExpenseService userExpenseService;
    public Expense addGroupExpense(CreateGroupExpense groupExpense){
        Group group = groupService.getGroup(groupExpense.getGroupId());
        Expense expense = Expense.builder()
                .description(groupExpense.getDescription())
                .amount(Double.valueOf(groupExpense.getAmount()))
                .group(group)
                .paidBy(new ArrayList<>())
                .owedBy(new ArrayList<>())
                .build();
        Expense savedExpense = expenseRepository.save(expense);

        UserExpense paidUser =  UserExpense.builder()
                .user(userService.getUser(groupExpense.getAddedBy()))
                .expense(savedExpense)
                .amount(Double.valueOf(groupExpense.getAmount()))
                .expenseType(ExpenseType.PAID)
                .build();
        userExpenseService.saveUserExpense(paidUser);
        savedExpense.getPaidBy().add(paidUser);
        List<User> groupMembers = group.getMembers();
        Double equalShare = getEqualShare(Double.valueOf(groupExpense.getAmount()), groupMembers.size());
        for(User user : groupMembers){
            UserExpense owedUser = UserExpense.builder()
                    .user(userService.getUser(user.getId()))
                    .expense(savedExpense)
                    .amount(equalShare)
                    .expenseType(ExpenseType.OWED)
                    .build();
            userExpenseService.saveUserExpense(owedUser);
            savedExpense.getOwedBy().add(owedUser);
        }
        return expenseRepository.save(savedExpense);
    }

    public Double getEqualShare(Double amount, Integer members){
        return amount/members;
    }
}


//u1 Expense g5 iPay 1000 Equal Desc Wifi Bill