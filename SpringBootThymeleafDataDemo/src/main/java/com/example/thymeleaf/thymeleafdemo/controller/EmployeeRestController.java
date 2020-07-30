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

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// theEmployee.setId(0);

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
	// add mapping for GET /employees/{employeeId}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee theEmployee = employeeService.findById(employeeId);

		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return theEmployee;
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// also just in case they pass an id in 9JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theEmployee.setId(0);

		employeeService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for PUT /employees - update existing employee

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for DELETE /employees/{employeeId} - delete employee

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee tempEmployee = employeeService.findById(employeeId);

		// throw exception if null

		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		employeeService.deleteById(employeeId);

		return "Deleted employee id - " + employeeId;
	}

}
