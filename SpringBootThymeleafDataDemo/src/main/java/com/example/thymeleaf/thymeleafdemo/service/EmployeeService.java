package com.example.thymeleaf.thymeleafdemo.service;

import java.util.List;

import com.example.thymeleaf.thymeleafdemo.entity.Employee;

public interface EmployeeService {

	public List<com.example.thymeleaf.thymeleafdemo.entity.Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(com.example.thymeleaf.thymeleafdemo.entity.Employee theEmployee);
	
	public void deleteById(int theId);
	
}
