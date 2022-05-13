package io.payrollmanagement.employeeservice;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.payrollmanagement.FeignServiceCompany;
//import io.payrollmanagement.ResponseD;
import io.payrollmanagement.employee.Company;
//import io.payrollmanagement.employee.Department;
import io.payrollmanagement.dto.RequestEmployee;
import io.payrollmanagement.dto.ResponseBuilderEmployee;
import io.payrollmanagement.employee.Employee;
import io.payrollmanagement.employee.Payslip;
import io.payrollmanagement.employeerepository.CompanyRepository;
import io.payrollmanagement.employeerepository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private FeignServiceCompany feignserviceutil;
	@Autowired
	private CompanyRepository companyRepository;

	public List<ResponseBuilderEmployee> getAllEmployee() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		List<ResponseBuilderEmployee> result = new ArrayList<>();
		for (int i = 0; i < employees.size(); i++) {
			Employee emp = employees.get(i);
			result.add(returnResponseBuilderEmployee(emp, ""));
		}
		return result;
	}

	public ResponseBuilderEmployee getEmployee(String Id) {
		try {
			Employee emp= employeeRepository.findAllByEmployeeId(Id);
			return (returnResponseBuilderEmployee(emp, "Employee Found"));
		} catch (Exception e) {
			return (ResponseBuilderEmployee.builder().message("Employee not found with this id").build());
		}
	}

	public ResponseBuilderEmployee addEmployee(RequestEmployee requestEmp, String id) {
		if (requestEmp.getEmployeeName() != null && requestEmp.getEmployeeContact() != null && requestEmp.getEmployeeAddress() != null && requestEmp.getEmployeeDept() != null
				&& requestEmp.getEmployeeDesign() != null) {
			String UUID1;
			UUID uuid = UUID.randomUUID();
			UUID1 = uuid.toString();
			while (employeeRepository.existsById(UUID1)) {
				UUID uui = UUID.randomUUID();
				UUID1 = uui.toString();
			}
			try {
				Company company = feignserviceutil.getCompanyOG(id);
				List<Employee> empList = feignserviceutil.getemployees(id);
				requestEmp.setEmployeeId(UUID1);
				Date date = new Date();
				requestEmp.setEmployeeTimestamp(date.toString());
				requestEmp.setDeleted(0);
				System.out.println("Lelo meri");
				employeeRepository.save(EmployeeBuilder(requestEmp, company));
				empList.add(EmployeeBuilder(requestEmp, company));
				company.setEmpList(empList);
				companyRepository.save(company);
				
				Employee emp = employeeRepository.findAllByEmployeeId(UUID1);
				return (returnResponseBuilderEmployee(emp, "Employee Added Succesfully"));
			} catch (Exception e) {
				return (ResponseBuilderEmployee.builder().message("Company Not Found"+e).build());
			}
		} else {
			return (ResponseBuilderEmployee.builder().message("Missing Details in Data").build());
		}
	}

	public ResponseBuilderEmployee updateEmployee(RequestEmployee requestEmp, String id) {
		try {
			Employee emp = employeeRepository.findAllByEmployeeId(id);
			Date date = new Date();
			requestEmp.setEmployeeTimestamp(date.toString());
			requestEmp.setDeleted(0);
			requestEmp.setEmployeeId(emp.getEmployeeId());
			employeeRepository.save(EmployeeBuilder(requestEmp));
			Employee employee = employeeRepository.findAllByEmployeeId(id);
			return (returnResponseBuilderEmployee(employee, "Employee Updated Succesfully"));
		} catch (Exception e) {
			return (ResponseBuilderEmployee.builder().message("Company with this ID Not Found").build());
		}
	}

	public String delEmployee(String id) {
		try {
			Employee emp = employeeRepository.findAllByEmployeeId(id);
			emp.setDeleted(1);
			return ("Employee Deleted Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Employee not found";
		}
	}

	public List<ResponseBuilderEmployee> getActiveEmployee() {
		List<Employee> activeEmployeeList = employeeRepository.findAllByDeleted(0);
		List<ResponseBuilderEmployee> result = new ArrayList<>();
		for (int i = 0; i < activeEmployeeList.size(); i++) {
			Employee emp = activeEmployeeList.get(i);
			result.add(returnResponseBuilderEmployee(emp, ""));
		}
		return result;
	}

	public ResponseBuilderEmployee returnResponseBuilderEmployee(Employee emp, String message) {
		return (ResponseBuilderEmployee.builder().employeeId(emp.getEmployeeId()).employeeName(emp.getEmployeeName()).employeeContact(emp.getEmployeeContact())
				.employeeAddress(emp.getEmployeeAddress()).employeeDept(emp.getEmployeeDept()).employeeDesign(emp.getEmployeeDesign()).employeeTimestamp(emp.getEmployeeTimestamp())
				.deleted(emp.getDeleted()).message(message).build());
	}

	public Employee EmployeeBuilder(RequestEmployee requestEmp) {
		return (Employee.builder().employeeId(requestEmp.getEmployeeId()).employeeName(requestEmp.getEmployeeName()).employeeContact(requestEmp.getEmployeeContact())
				.employeeAddress(requestEmp.getEmployeeAddress()).employeeDept(requestEmp.getEmployeeDept()).employeeDesign(requestEmp.getEmployeeDesign()).employeeTimestamp(requestEmp.getEmployeeTimestamp())
				.deleted(requestEmp.getDeleted()).build());
	}

	public Employee EmployeeBuilder(RequestEmployee requestEmp, Company company) {
		return (Employee.builder().employeeId(requestEmp.getEmployeeId()).company(company).employeeName(requestEmp.getEmployeeName()).employeeContact(requestEmp.getEmployeeContact())
				.employeeAddress(requestEmp.getEmployeeAddress()).employeeDept(requestEmp.getEmployeeDept()).employeeDesign(requestEmp.getEmployeeDesign()).employeeTimestamp(requestEmp.getEmployeeTimestamp())
				.deleted(requestEmp.getDeleted()).build());
	}

	public Employee getEmployeeOG(String id) {
		Employee emp = employeeRepository.findAllByEmployeeId(id);
		return emp;
	}

	public List<Payslip> getEmployeeSalaryslips(String id) {
		Employee emp = employeeRepository.findAllByEmployeeId(id);
		return emp.getPaysliplist();
	}

	public Payslip getEmployeeSalaryslip(String id, String month, int year) {
		Employee emp= employeeRepository.findAllByEmployeeId(id);
		String MY=month+Integer.toString(year);
		List<Payslip> paysliplist=emp.getPaysliplist();
		for (int i = 0; i < paysliplist.size(); i++) 
		{
			Payslip payslip1=paysliplist.get(i);
			if (payslip1.getMonthandyear()==MY)
			{
				return payslip1;
			}
	}
	return(Payslip.builder()
			.monthandyear("Not Found")
			.build());

}
}
