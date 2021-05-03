package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.service.EmployeeService;

@SpringBootApplication
public class ActivitiExApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiExApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final EmployeeService employeeService) {

		return new CommandLineRunner() {
			public void run(String... strings) throws Exception {
				employeeService.createPersons();
			}
		};
	}
}
