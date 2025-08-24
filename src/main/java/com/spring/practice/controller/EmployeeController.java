package com.spring.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.spring.practice.pojo.Employee;
import com.spring.practice.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PostMapping("/saveAllEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> saveEmployee(@RequestBody List<Employee> employee) {
        return employeeService.saveAllEmployee(employee);
    }

    @GetMapping("/processEmployee")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Employee>> processEmployee() {
        return employeeService.processAllEmployee();
    }

    @PostMapping("/increaseSalary/{percent}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> increaseSalaryAll(@PathVariable Double percent) {
        try {
            return employeeService.increaseSalary(percent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating salaries: " + e.getMessage());
        }
    }

    @GetMapping("/getAllEmployee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @PostMapping("/updateSalary/{id}/{salary}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateSalary(@PathVariable Long id, @PathVariable Double salary) {
        return employeeService.updateSalary(id, salary);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.delEmployee(id);
    }

    @PutMapping("/updateValue/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
