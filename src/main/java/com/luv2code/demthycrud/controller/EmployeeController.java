package com.luv2code.demthycrud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.luv2code.demthycrud.entity.Employee;
import com.luv2code.demthycrud.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService=employeeService;
	}

	@GetMapping("list")
	public String find(Model model)
	{
		List<Employee> theEmployees = employeeService.getAllEmployees();
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
			employeeService.createEmployee(employee);
			return "redirect:/";
		}
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model)
	{
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee",employee);
		return "employees/employees-form";
	}
	
	@GetMapping("/DeleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int id)
	{
		employeeService.deleteEmployee(id);
		return "redirect:/";
	}
	
}
