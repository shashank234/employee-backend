package com.spring.practice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.practice.dao.EmployeeDao;
import com.spring.practice.pojo.Employee;
import com.spring.practice.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeDao employeeDao;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public ResponseEntity<List<Employee>> saveAllEmployee(List<Employee> employee) {
		return new ResponseEntity<>(employeeRepository.saveAll(employee),HttpStatus.OK);
	}
	
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}
	
	public ResponseEntity<?> increaseSalary(Double salaryPercent) {
		List<Employee> employees = employeeRepository.findAll();
		try {
	        return employeeDao.increaseSalary(employees, salaryPercent);
	    } catch (Exception e) {
	        // Catch any unexpected exceptions and return a proper response
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while updating salaries: " + e.getMessage());
	    }
	}
	
	public ResponseEntity<List<Employee>> processAllEmployee() {
		List<Employee> employees = employeeRepository.findAll().stream().map( employee -> employeeDao.processTax(employee) )
													.collect(Collectors.toList());
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	public ResponseEntity<String> delEmployee(Long id) {
		int res = employeeRepository.deleteEmployeeById(id);
		if (res>0) {
			return new ResponseEntity<>("Employee deleted succssfully",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Employee failed to delete",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	public ResponseEntity<String> updateSalary(Long id,Double salary) {
		int res = employeeRepository.updateSalary(id, salary);
		Optional<Employee> employees = employeeRepository.findById(id);
		if (employees.isPresent()) {
			List<Employee> employeeToUpdateTax = employees.stream().toList();
			employeeToUpdateTax = employeeToUpdateTax.stream().map( employee -> employeeDao.processTax(employee) )
					.collect(Collectors.toList());
		}
		if (res>0 && employees.isPresent()) {
			return new ResponseEntity<>("Salary updated scuccessfully",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Salary failed to update scuccessfully",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
		Optional<Employee> exists = employeeRepository.findById(id);
		if (exists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		employee.setId(id);
		employee = employeeRepository.save(employee);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
