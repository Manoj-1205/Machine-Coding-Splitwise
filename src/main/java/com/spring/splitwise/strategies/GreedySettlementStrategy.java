package com.spring.splitwise.strategies;

import com.spring.splitwise.dtos.Transaction;
import com.spring.splitwise.models.Expense;
import com.spring.splitwise.models.User;
import com.spring.splitwise.models.UserExpense;
import org.springframework.data.util.Pair;

import java.util.*;

public class GreedySettlementStrategy implements SettlementStrategy{

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        Map<Long, Double> balances = calculateBalances(expenses);
        TreeSet<Pair<Long, Double>> expenseTree=new TreeSet<>(
                (expense1, expense2) ->
                        (int) (expense1.getSecond() - expense2.getSecond())
        );
        for(Map.Entry<Long, Double> balance : balances.entrySet()){
            expenseTree.add(Pair.of(balance.getKey(), balance.getValue()));
        }
        List<Transaction> transactions=new ArrayList<>();
        while(expenseTree.size() > 1){
            //Minimum value
            Pair<Long, Double> smallestPair = expenseTree.first(); //-500
            //Maximum Value
            Pair<Long, Double> largestPair = expenseTree.last(); //600
            //Pay from minimum to maximum
            Transaction transaction = Transaction.builder()
                    .from(largestPair.getFirst()) //-500
                    .to(smallestPair.getFirst()) //600
                    .amount(smallestPair.getSecond())
                    .build();

            //Remove both users from the heap
            expenseTree.remove(largestPair);
            expenseTree.remove(smallestPair);

            //If the amount is not exact
            expenseTree.add(Pair.of(smallestPair.getFirst(), smallestPair.getSecond() + largestPair.getSecond()));

            transactions.add(transaction);

        }
        return transactions;
    }
    private static Map<Long, Double> calculateBalances(List<Expense> expenses){
        Map<Long, Double> balances = new HashMap<>();
        for(Expense expense : expenses){
            //Iterate each expense in paidBy and Owed by
             for(UserExpense userExpense : expense.getOwedBy()){
                 User user = userExpense.getUser();
                 if(!balances.containsKey(user.getId())){
                     balances.put(user.getId(), 0.0);
                 }
                 balances.put(user.getId(), balances.get(user.getId()) + userExpense.getAmount());

             }

             for(UserExpense userExpense : expense.getPaidBy()){
                 User user = userExpense.getUser();
                 if(!balances.containsKey(user.getId())){
                     balances.put(user.getId(), 0.0);
                 }
                 balances.put(user.getId(), balances.get(user.getId()) - userExpense.getAmount());
             }
        }

        return balances;
    }
}
