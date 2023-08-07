package com.spring.splitwise.commands;

//Abstraction over all commands
public interface Command {
    boolean matches(String input);
    void execute(String input);
}
