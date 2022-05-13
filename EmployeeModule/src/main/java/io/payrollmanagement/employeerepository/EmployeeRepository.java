package io.payrollmanagement.employeerepository;

import java.util.List;

//import java.util.Optional;

//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import io.payrollmanagement.employee.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Employee findAllByEmployeeId(String id);

	List<Employee> findAllByDeleted(int d);

}