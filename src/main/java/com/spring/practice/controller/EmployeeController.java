package com.spring.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.practice.pojo.Employee;
import com.spring.practice.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;
	
	@RequestMapping(value = "/saveEmployee" , method = RequestMethod.POST)
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@RequestMapping(value = "/saveAllEmployee" , method = RequestMethod.POST)
	public ResponseEntity<List<Employee>> saveEmployee(@RequestBody List<Employee> employee) {
		return employeeService.saveAllEmployee(employee);
	}

	@RequestMapping(value = "/processEmployee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> processEmployee() {
		return employeeService.processAllEmployee();
	}
	
	@RequestMapping(value = "/increaseSalary/{percent}", method = RequestMethod.POST)
	public ResponseEntity<?> increaseSalaryAll(@PathVariable Double percent) {
		try {
	        return employeeService.increaseSalary(percent);
	    } catch (Exception e) {
	        // Catch any unexpected exceptions and return a proper response
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while updating salaries: " + e.getMessage());
	    }
	}
	
	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET)
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}
	
	@RequestMapping(value = "/updateSalary/{id}/{salary}", method = RequestMethod.POST)
	public ResponseEntity<String> updateSalary(@PathVariable Long id, @PathVariable Double salary) {
		return  employeeService.updateSalary(id, salary);
	}
	
	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		return employeeService.delEmployee(id);
	}
	
	@RequestMapping(value = "/updateValue/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}
}
