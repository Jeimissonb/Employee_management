package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Employee;
import com.example.backend.repository.EmployeeRepository;

@CrossOrigin (origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees (){
		return employeeRepository.findAll();
	}
	
	//create add employee
	@PostMapping("/employees")
	public Employee createEmployee (@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
}
