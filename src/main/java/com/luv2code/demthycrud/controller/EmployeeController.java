package com.luv2code.demthycrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.luv2code.demthycrud.dao.EmployeeRepository;
import com.luv2code.demthycrud.entity.Employee;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeController(EmployeeRepository empl)
	{
		this.employeeRepository=empl;
	}

	@GetMapping("list")
	public String find(Model model)
	{
		List<Employee> theEmployees = employeeRepository.findAllByOrderByLastNameAsc();
		model.addAttribute("employee",theEmployees);
		return "employees/list-employees";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormAdd(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		return "employees/employees-form";
	}
	
	@PostMapping("/NewEmployees")
	public String createEmployees(@Valid @ModelAttribute("employee") Employee employee,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "employees/employees-form";
		}
		else
		{
			employeeRepository.save(employee);
			return "redirect:/";
		}
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model)
	{
		Optional<Employee> op = employeeRepository.findById(id);
		Employee employee = null;
		if(op.isPresent())
		{
			employee = op.get();
		}
		else
		{
			throw new RuntimeException("Did not find employee id - " + id);
		}
		model.addAttribute("employee",employee);
		return "employees/employees-form";
	}
	
	@GetMapping("/DeleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int id)
	{
		employeeRepository.deleteById(id);
		return "redirect:/";
	}
	
}
