package com.spring.splitwise.models;

import com.spring.splitwise.enums.ExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserExpense extends BaseModel{

    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
}
