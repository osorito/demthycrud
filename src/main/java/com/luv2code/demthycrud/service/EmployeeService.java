package com.luv2code.demthycrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.demthycrud.dao.EmployeeRepository;
import com.luv2code.demthycrud.entity.Employee;

import org.springframework.data.domain.*;

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
	
	public Page<Employee> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection)
	{
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber -1, pageSize,sort);
		return employeeRepository.findAll(pageable);
	}
}
