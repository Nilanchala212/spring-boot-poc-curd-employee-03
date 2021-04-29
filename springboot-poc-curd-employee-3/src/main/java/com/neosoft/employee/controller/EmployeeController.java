package com.neosoft.employee.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.employee.exception.ResourceNotFountException;
import com.neosoft.employee.model.Employee;
import com.neosoft.employee.repo.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return repository.save(employee);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFountException("Employee not exit with id:" + id));
		return ResponseEntity.ok(employee);

	}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employeeDetails) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFountException("Employee not exit with id:" + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setCity(employeeDetails.getCity());
		Employee updatedEmployee=repository.save(employee);
		return ResponseEntity.ok(updatedEmployee);

	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFountException("Employee not exit with id:" + id));
		
		repository.delete(employee);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	@GetMapping("/fetchuserbydob")
	public List<Employee> sortUserByDob() {
		List<Employee> userlist = repository.featchUsersByDob();
		return userlist;
	}

	@GetMapping("/fetchuserbydoj")
	public List<Employee> sortUserByDoj() {
		List<Employee> userlist1 = repository.featchUserByDoj();
		return userlist1;
	}


}
