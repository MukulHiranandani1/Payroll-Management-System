package io.payrollmanagement.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.payrollmanagement.department.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {
	
	Department findAllByDepartmentId(String id);
	List<Department> findAllByDeleted(int l);
	
}