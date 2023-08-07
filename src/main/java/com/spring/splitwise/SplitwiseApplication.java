package com.spring.splitwise;

import com.spring.splitwise.commands.*;
import com.spring.splitwise.dtos.CreateUserRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.SQLOutput;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
	//CommandLineRunner class provided by Spring and can be used to create command line applications
	private final Scanner scanner;
	private final CommandExecutor commandExecutor;
	public SplitwiseApplication(CommandExecutor executor, CreateUserCommand createUserCommand, AddGroupCommand addGroupCommand, AddMemberCommand addMemberCommand, AddExpenseCommand addExpenseCommand){
		this.scanner = new Scanner(System.in);
		this.commandExecutor = executor;
		this.commandExecutor.addCommand(createUserCommand);
		this.commandExecutor.addCommand(addGroupCommand);
		this.commandExecutor.addCommand(addMemberCommand);
		this.commandExecutor.addCommand(addExpenseCommand);
	}
	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while(true){
			System.out.println("Enter input : ");
			String input = scanner.nextLine();
			System.out.println("User entered : "+input);
			commandExecutor.execute(input);
		}
	}
}
