package com.spring.splitwise.commands;

import org.springframework.stereotype.Component;

@Component
public class SettleUpCommand implements Command{
    @Override
    public boolean matches(String input) {
        return false;
    }

    @Override
    public void execute(String input) {

    }
}
