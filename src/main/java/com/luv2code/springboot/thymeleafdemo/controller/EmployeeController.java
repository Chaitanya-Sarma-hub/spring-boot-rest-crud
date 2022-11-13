package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> employees = empService.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		empService.save(employee);
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model) {
		Employee employee = empService.findById(theId);
		model.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		empService.deleteById(id);
		return "redirect:/employees/list";
	}

}
