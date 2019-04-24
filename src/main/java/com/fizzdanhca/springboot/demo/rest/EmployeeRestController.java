package com.fizzdanhca.springboot.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fizzdanhca.springboot.demo.dao.EmployeeDAO;
import com.fizzdanhca.springboot.demo.entity.Employee;
import com.fizzdanhca.springboot.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	//tiêm employee Dao
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	// tạo nhánh "/employees" và trả lại kết quả list các employee
	@GetMapping("/employees")
	public List<Employee>  findAll(){
		return employeeService.findAll();
		
	}
	
	// tạo nhánh "/employees/{employeeId} cho GET
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee is not found" + employeeId);
		}
		return theEmployee;
	}
	
	// thêm mới 1 employee bằng POST /employees 
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		// JSON set id cho = 0
		// tập trung vào save item mới thay vì update
		////// setId(9);
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
}
