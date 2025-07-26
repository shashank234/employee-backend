package com.spring.practice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.spring.practice.pojo.Employee;
import com.spring.practice.repository.EmployeeRepository;

@Component
public class EmployeeDao {
	
	@Autowired
	public EmployeeRepository employeeRepo;
	
	public synchronized Employee processTax(Employee employee) {
		Double salary = employee.getSalary();
		Double tax = salary / 30;
		Long round = Math.round(tax);
		employee.setTax(round);
		employeeRepo.save(employee);
		return employee;
	}
	
	public ResponseEntity<?> increaseSalary(List<Employee> employees,Double salaryPercent) {
	    AtomicInteger count = new AtomicInteger(0);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		try {
	        List<Future<?>> futures = new ArrayList<>();
	        employees.forEach(employee -> {
	            Future<?> future = executorService.submit(() -> {
	                Double hikedSalary = (employee.getSalary() * salaryPercent) / 100;
	                Double newSalary = hikedSalary + employee.getSalary();
	                int result = employeeRepo.updateSalary(employee.getId(), newSalary);
	                if (result > 0) {
	                    count.incrementAndGet(); // Increment count safely
	                }
	            });
	            futures.add(future);
	        });

	        for (Future<?> future : futures) {
	            try {
	                future.get(); // Wait for the task to finish
	            } catch (Exception e) {
	                e.printStackTrace();
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                					  .body("Failed to update salary due to an error: " + e.getMessage());
	            }
	        }

	        // Check if all employees' salaries were updated
	        if (count.get() == employees.size()) {
	            return new ResponseEntity<>(employeeRepo.findAll(), HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            		.body("Error while proceesing Increase Salary");
	        }
	    } finally {
	        executorService.shutdown(); // Ensure the executor service is shut down
	    }
		
	}
}
