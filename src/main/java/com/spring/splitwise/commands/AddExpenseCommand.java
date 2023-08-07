package com.spring.splitwise.commands;

import com.spring.splitwise.controllers.ExpenseController;
import com.spring.splitwise.dtos.CreateGroupExpense;
import com.spring.splitwise.models.Expense;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

    //u1 Expense g1 iPay 1000 Equal Desc Wifi Bill
@Component
@AllArgsConstructor
public class AddExpenseCommand implements Command{
    private ExpenseController expenseController;
    @Override
    public boolean matches(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        if(!tokens.get(1).equals(Commands.ADD_Expense))
            return false;
        return tokens.size()>=6;
    }

        //u1 Expense g1 iPay 1000 Equal Desc Wifi Bill
    @Override
    public void execute(String input) {
        List<String> tokens = Arrays.stream(input.split(" ")).toList();
        String description = input.split("Desc ")[1];
       if(tokens.get(2).startsWith("g")){
           if(tokens.get(3).equals(PaymentType.IPAY)){
               CreateGroupExpense groupExpense = CreateGroupExpense.builder()
                       .addedBy(getIdFromString(tokens.get(0)))
                       .groupId(getIdFromString(tokens.get(2)))
                       .paymentType(tokens.get(3))
                       .amount(Integer.parseInt(tokens.get(4)))
                       .splitType(tokens.get(5))
                       .description(description)
                       .build();
               Expense expense = expenseController.addGroupExpense(groupExpense);
               System.out.println("Group Expense added.");

           }
           else{

           }

       }
    }

    private Long getIdFromString(String str){
        return Long.parseLong(str.substring(1));
    }
}
