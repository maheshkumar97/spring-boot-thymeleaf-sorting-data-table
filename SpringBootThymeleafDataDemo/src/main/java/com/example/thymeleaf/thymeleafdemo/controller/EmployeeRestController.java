package com.example.thymeleaf.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.thymeleaf.thymeleafdemo.entity.Employee;
import com.example.thymeleaf.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/api")
@ComponentScan("com.example.thymeleaf")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public String findAll(Model model) {
		List list = employeeService.findAll();
		model.addAttribute("list", list);
		return "employee-style";
	}

	// add mapping for showFormForAdd
	@GetMapping("/showFormFormAdd")
	public String showFormForAdd(Model model) {
		Employee theEmployee = new Employee();

		model.addAttribute("employee", theEmployee);
		return "employee-form";
	}

	// add mapping to save data
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		employeeService.save(theEmployee);

		return "redirect:/api/employees";
	}

	// add mapping for update a particular employee

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {

		Employee theEmployee = employeeService.findById(id);
		model.addAttribute("employee", theEmployee);

		return "employee-form";
	}

	// add mapping for Delete a particular Employee

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {

		employeeService.deleteById(id);

		return "redirect:/api/employees";
	}

}
