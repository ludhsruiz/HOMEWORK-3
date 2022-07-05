package com.ironhack.homework_3;

import com.ironhack.homework_3.menu.Menu;
import com.ironhack.homework_3.menu.Printer;
import com.ironhack.homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Homework3Application implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private SalesRepsRepository salesRepsRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private OpportunityRepository opportunityRepository;



	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Homework3Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Menu menu = new Menu(accountRepository, salesRepsRepository, contactRepository, leadRepository, opportunityRepository);
		Printer printer = new Printer();
		printer.welcomeMessage();
		menu.controlLoop();
	}


}
