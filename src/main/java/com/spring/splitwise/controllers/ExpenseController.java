package com.spring.splitwise.controllers;

import com.spring.splitwise.Service.ExpenseService;
import com.spring.splitwise.dtos.CreateGroupExpense;
import com.spring.splitwise.models.Expense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ExpenseController {
    private ExpenseService expenseService;
    public Expense addGroupExpense(CreateGroupExpense groupExpense){
        return expenseService.addGroupExpense(groupExpense);
    }
}
