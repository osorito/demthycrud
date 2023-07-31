package com.luv2code.demthycrud.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.demthycrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	List<Employee> findAllByOrderByLastNameAsc();
	Page<Employee> findAllByOrderByFirstName(Pageable pageable);
}
