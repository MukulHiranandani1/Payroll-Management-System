package io.payrollmanagement.company.controller;

import java.util.List;

import io.payrollmanagement.company.Company;
import io.payrollmanagement.company.Department;
import io.payrollmanagement.company.Employee;
import io.payrollmanagement.company.service.Companyservice;
import io.payrollmanagement.dto.RequestCompany;
import io.payrollmanagement.dto.ResponseBuilderCompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Companycontroller {
	@Autowired
	private Companyservice companyService;
	Logger logger=LoggerFactory.getLogger(Companycontroller.class);

	@RequestMapping(value="/companies/all")
	public List<ResponseBuilderCompany> getallcompanies() {
		return companyService.getAllCompany();
	}

	@RequestMapping(value="/companies/active")
	public List<ResponseBuilderCompany> getActiveCompanies() {
		return companyService.getActiveCompany();
	}

	@RequestMapping(value="/companies/{id}")
	public ResponseBuilderCompany getCompany(@PathVariable String id) {
			return companyService.getCompany(id);
		}
	
	

	@RequestMapping(method = RequestMethod.POST, value = "/companies")
	public ResponseBuilderCompany addCompany(@RequestBody RequestCompany requestCompany) {
		return companyService.addCompany(requestCompany);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/companies/{id}")
	public ResponseBuilderCompany updateCompany(@RequestBody RequestCompany requestCompany, @PathVariable String id) {
		return companyService.updateCompany(requestCompany, id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/companies/{id}")
	public String delCompany(@PathVariable String id) {
		return companyService.delCompany(id);
	}
	
	@RequestMapping(value="/companies/{id}/departments")
	public List<Department> getdepartments(@PathVariable String id) {
		return companyService.getdepartments(id);
	}
	
	@RequestMapping(value="/companies/{id}/employees")
	public List<Employee> getemployees(@PathVariable String id) {
		return companyService.getemployees(id);
	}
	@RequestMapping(value="/companies/og/{id}")
	public Company getCompanyOG(@PathVariable String id) {
		return companyService.getCompanyOG(id);
	}
}