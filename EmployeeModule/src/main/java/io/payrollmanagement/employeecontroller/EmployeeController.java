package io.payrollmanagement.employeecontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.payrollmanagement.dto.RequestEmployee;
import io.payrollmanagement.dto.ResponseBuilderEmployee;
import io.payrollmanagement.employee.Employee;
import io.payrollmanagement.employee.Payslip;
import io.payrollmanagement.employeeservice.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	@RequestMapping("/employees/all")
	public List<ResponseBuilderEmployee> getallemployees() {
		return employeeService.getAllEmployee();
	}//chiye nhi

	@RequestMapping("/employees/{id}")
	public ResponseBuilderEmployee getEmployee(@PathVariable String id) {
		return employeeService.getEmployee(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employees/{id}")
	public ResponseBuilderEmployee addEmployee(@RequestBody RequestEmployee requestEmp, @PathVariable String id) {
		return employeeService.addEmployee(requestEmp,id);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}")
	public ResponseBuilderEmployee updateEmployee(@RequestBody RequestEmployee requestEmp, @PathVariable String id) {
		return employeeService.updateEmployee(requestEmp, id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
	public String delEmployee(@PathVariable String id) {

		return employeeService.delEmployee(id);
	}

	@RequestMapping("/activeEmployees")
	public List<ResponseBuilderEmployee> getActiveEmployee() {
		return employeeService.getActiveEmployee();
	}//Isko update kro
	
	@RequestMapping("/employees/og/{id}")
	public Employee getEmployeeOG(@PathVariable String id) {
		return employeeService.getEmployeeOG(id);
	}
	
	@RequestMapping("/employees/{id}/salaryslips")
	public List<Payslip> getsalarySlips(@PathVariable String id){
		return employeeService.getEmployeeSalaryslips(id);
	}
	
	@RequestMapping("/employees/{id}/salaryslips/{month}/{year}")
	public Payslip getsalarySlip(@PathVariable String id,@PathVariable String month,@PathVariable int year){
		return employeeService.getEmployeeSalaryslip(id,month,year);
	}
}
