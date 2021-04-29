package com.neosoft.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query(value = "select * from user ORDER BY dob ASC",nativeQuery = true)
	List<Employee> featchUsersByDob();
	
	@Query(value = "select * from user ORDER BY doj ASC",nativeQuery = true)
	List<Employee> featchUserByDoj();
	

}
