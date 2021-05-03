package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class EmployeeService {

	@Autowired
	private PersonRepository personRepository;

	// create the list of Employees into the database who perform the task
	public void createPersons() {

		if (personRepository.findAll().size() == 0) {

			personRepository.save(new Person("Person1"));
			personRepository.save(new Person("Person2"));
			personRepository.save(new Person("Person3"));
		}
	}
}
