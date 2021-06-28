package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.exception.ResourceNotFoundException;
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
	
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getByEmployeeId (@PathVariable Long id){
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not found with informed id: "+id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee (@PathVariable Long id, @RequestBody Employee dataEmployee){
		Employee employee = employeeRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee to update not found with this id:"+id));
		employee.setFirstName(dataEmployee.getFirstName());
		employee.setLastName(dataEmployee.getLastName());
		employee.setEmailId(dataEmployee.getEmailId());
		
		Employee employeeNew = employeeRepository.save(employee);
		return ResponseEntity.ok(employeeNew);
	}
	
	
}
