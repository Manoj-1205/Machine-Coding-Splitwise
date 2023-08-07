package com.spring.splitwise.strategies;

import com.spring.splitwise.dtos.Transaction;
import com.spring.splitwise.models.Expense;

import java.util.List;

public interface SettlementStrategy {
    List<Transaction> settleUp(List<Expense> expenses);
}
