package io.payrollmanagement.service;

import java.util.Date;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.payrollmanagement.FeignServiceUtil;
import io.payrollmanagement.repository.CompanyRepository;
import io.payrollmanagement.department.Company;
import io.payrollmanagement.department.Department;
import io.payrollmanagement.dto.RequestDepartment;
import io.payrollmanagement.dto.ResponseDepartment;
import io.payrollmanagement.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private FeignServiceUtil feignserviceutil;
	@Autowired
	private CompanyRepository companyRepository;

	public List<ResponseDepartment> getAllDepartment() {
		List<Department> departments = new ArrayList<>();
		departmentRepository.findAll().forEach(departments::add);
		List<ResponseDepartment> result = new ArrayList<>();
		for (int i = 0; i < departments.size(); i++) {
			Department dept= departments.get(i);
			result.add(ResponseDepartment.builder().departmentId(dept.getDepartmentId()).departmentName(dept.getDepartmentName())
					.departmentHead(dept.getDepartmentHead()).departmentTimestamp(dept.getDepartmentTimestamp())
					.deleted(dept.getDeleted()).build());
		}
		return result;
	}

	public ResponseDepartment getDepartment(String Id) {
		try {
			Department dept = departmentRepository.findAllByDepartmentId(Id);
			return (returnResponseDepartment(dept,"Department found"));
		} catch (Exception e) {
			return (ResponseDepartment.builder().message("No department found with this id").build());
		}
	}

	public ResponseDepartment addDepartment(String id, RequestDepartment requestDept) {
		if (requestDept.getDepartmentHead() != null && requestDept.getDepartmentName() != null) {
			String UUID1;
			UUID uuid = UUID.randomUUID();
			UUID1 = uuid.toString();
			while (departmentRepository.existsById(UUID1)) {
				UUID uui = UUID.randomUUID();
				UUID1 = uui.toString();
			}
			Company company = feignserviceutil.getCompanyOG(id);
			List<Department> dlist = feignserviceutil.getdepartments(id);
			requestDept.setDepartmentId(UUID1);
			requestDept.setDeleted(0);
			Date date = new Date();
			requestDept.setDepartmentTimestamp(date.toString());
			departmentRepository.save(DepartmentBuilder(requestDept, company));
			dlist.add(DepartmentBuilder(requestDept, company));
			company.setDeptList(dlist);
			companyRepository.save(company);
			Department dept = departmentRepository.findAllByDepartmentId(UUID1);
			return (returnResponseDepartment(dept,"Department Added"));
		} else {
			return (ResponseDepartment.builder().message("Department not Added").build());
		}
	}

	public ResponseDepartment updateDepartment(RequestDepartment requestDept, String id) {
		try {
			Department dept = departmentRepository.findAllByDepartmentId(id);
			Date date = new Date();
			requestDept.setDepartmentTimestamp(date.toString());
			requestDept.setDeleted(0);
			requestDept.setDepartmentId(requestDept.getDepartmentId());
			departmentRepository.save(DepartmentBuilder(requestDept));
			Department department = departmentRepository.findAllByDepartmentId(id);
			return (returnResponseDepartment(department,"Department Updated"));
		} catch (Exception e) {
			return (ResponseDepartment.builder().message("No department found with this id").build());
		}
	}

	public String delDepartment(String id) {
		try {
			Department dept = departmentRepository.findAllByDepartmentId(id);
			dept.setDeleted(1);
			departmentRepository.save(dept);
			return ("Department Deleted Successfully");
		} catch (Exception e) {
			return "Department not found";
		}
	}

	public List<ResponseDepartment> getActiveDepartment() {
		List<Department> activeDepartmentList = departmentRepository.findAllByDeleted(0);
		List<ResponseDepartment> result = new ArrayList<>();
		for (int i = 0; i < activeDepartmentList.size(); i++) {
			Department dept = activeDepartmentList.get(i);
			result.add(returnResponseDepartment(dept,""));
		}
		return result;
	}

	public ResponseDepartment returnResponseDepartment(Department dept,String message) {
		return (ResponseDepartment.builder().departmentId(dept.getDepartmentId()).departmentName(dept.getDepartmentName())
				.departmentHead(dept.getDepartmentHead()).departmentTimestamp(dept.getDepartmentTimestamp())
				.deleted(dept.getDeleted()).message(message)
				.build());
	}

	public Department DepartmentBuilder(RequestDepartment requestDept) {
		return (Department.builder().departmentId(requestDept.getDepartmentId()).departmentName(requestDept.getDepartmentName())
				.departmentHead(requestDept.getDepartmentHead()).deleted(requestDept.getDeleted())
				.departmentTimestamp(requestDept.getDepartmentTimestamp()).build());
	}

	public Department DepartmentBuilder(RequestDepartment requestDept, Company company) {
		return (Department.builder().company(company).departmentId(requestDept.getDepartmentId()).departmentName(requestDept.getDepartmentName())
				.departmentHead(requestDept.getDepartmentHead()).deleted(requestDept.getDeleted())
				.departmentTimestamp(requestDept.getDepartmentTimestamp()).build());
	}

}