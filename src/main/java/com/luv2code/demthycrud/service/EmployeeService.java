package com.luv2code.demthycrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.demthycrud.dao.EmployeeRepository;
import com.luv2code.demthycrud.entity.Employee;

@Service
public class EmployeeService {
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository)
	{
		this.employeeRepository=employeeRepository;
	}
	
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAllByOrderByLastNameAsc();
	}
	
	public Employee getEmployeeById(int theId)
	{
		Optional<Employee> op = employeeRepository.findById(theId);
		Employee employee = null;
		if(op.isPresent())
		{
			employee = op.get();
		}
		else
		{
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		return employee;
	}
	
	public Employee createEmployee(Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	
	public void deleteEmployee(int theId)
	{
		employeeRepository.deleteById(theId);
	}
}
