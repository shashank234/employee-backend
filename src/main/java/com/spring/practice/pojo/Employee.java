package com.spring.practice.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private Double salary;
    private Double returns;
    private Long tax;
    private Double professionalTax;
    
    // Default constructor
    public Employee() {}

    // Constructor for initial creation
    public Employee(String firstName, String lastName, String department, Double salary, Double returns, Long tax, Double professionalTax) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.returns = returns;
        this.tax = tax;
        this.professionalTax = professionalTax;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getReturns() {
		return returns;
	}

	public void setReturns(Double returns) {
		this.returns = returns;
	}

	public Long getTax() {
		return tax;
	}

	public void setTax(Long tax) {
		this.tax = tax;
	}

	public Double getProfessionalTax() {
		return professionalTax;
	}

	public void setProfessionalTax(Double professionalTax) {
		this.professionalTax = professionalTax;
	}

	@Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department=" + department +
               ", salary=" + salary + ", returns=" + returns + ", tax=" + tax + ", professionalTax=" + professionalTax + "]";
    }
}
