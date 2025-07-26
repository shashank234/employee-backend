package com.spring.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.practice.pojo.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Modifying
	@Transactional
	@Query("Delete from Employee where id = :id")
	int deleteEmployeeById(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("Update Employee e set e.salary = :salary where e.id = :id")
	int updateSalary(@Param("id") Long id, @Param("salary") Double salary);
}
