package com.spring.splitwise.Repositories;

import com.spring.splitwise.controllers.ExpenseController;
import com.spring.splitwise.models.Expense;
import com.spring.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListResourceBundle;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
