package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;


@Service
public class ProcessService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	// start the process and set Person as variable
	public String startTheProcess(String assignee) {

		Person person = personRepository.findByName(assignee);

		Map<String, Object> variables = new HashMap<>();
		variables.put("person", person);

		runtimeService.startProcessInstanceByKey("second", variables);

		return processInformation();
	}

	// fetching process and task information
	public String processInformation() {
		//get all tasks
		List<Task> taskList = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
		StringBuilder processAndTaskInfo = new StringBuilder();
		
		processAndTaskInfo.append("Number of process definition available: "
				+ repositoryService.createProcessDefinitionQuery().count() + " | Task Details= ");

		taskList.forEach(task -> {

			processAndTaskInfo.append("ID: " + task.getId() + ", Name: " + task.getName() + ", Assignee: "
					+ task.getAssignee() + ", Description: " + task.getDescription());
		});

		return processAndTaskInfo.toString();
	}

	// fetch task assigned to Person
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}

	// complete the task
	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}
}