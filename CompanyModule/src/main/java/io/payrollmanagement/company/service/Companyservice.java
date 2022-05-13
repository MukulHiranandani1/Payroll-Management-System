package io.payrollmanagement.company.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.payrollmanagement.company.Company;
import io.payrollmanagement.company.Department;
import io.payrollmanagement.company.Employee;
import io.payrollmanagement.company.repository.CompanyRepository;
import io.payrollmanagement.dto.RequestCompany;
import io.payrollmanagement.dto.ResponseBuilderCompany;

@Service
public class Companyservice {

	@Autowired
	private CompanyRepository companyRepository;

	public List<ResponseBuilderCompany> getActiveCompany() {
		List<Company> activeCompanyList = companyRepository.findAllByDeleted(0);
		List<ResponseBuilderCompany> result = new ArrayList<>();
		for (int i = 0; i < activeCompanyList.size(); i++) {
			Company company = activeCompanyList.get(i);
			result.add(returnResponseBuilderCompany(company, ""));
		}
		return result;
	}

	public List<ResponseBuilderCompany> getAllCompany() {
		List<Company> companies = new ArrayList<>();
		companyRepository.findAll().forEach(companies::add);
		List<ResponseBuilderCompany> result = new ArrayList<>();
		for (int i = 0; i < companies.size(); i++) {
			Company company = companies.get(i);
			result.add(returnResponseBuilderCompany(company, ""));
		}
		return result;
	}

	public ResponseBuilderCompany getCompany(String Id) {
		try {
			Company company= companyRepository.findAllByCompanyId(Id);
			return (returnResponseBuilderCompany(company, ""));
		} catch (Exception e) {
			return (ResponseBuilderCompany.builder().message("Company with this ID Not Found").build());
		}
	}

	public ResponseBuilderCompany addCompany(RequestCompany requestCompany) {
		if (requestCompany.getCompanyName() != null && requestCompany.getCompanyAddress() != null && requestCompany.getCompanyCEO() != null
				&& requestCompany.getCompanyContact() != null && requestCompany.getCompanyYOE() != null) {
			String UUID1;
			UUID uuid = UUID.randomUUID();
			UUID1 = uuid.toString();
			while (companyRepository.existsById(UUID1)) {
				UUID uui = UUID.randomUUID();
				UUID1 = uui.toString();
			}
			requestCompany.setCompanyId(UUID1);
			requestCompany.setDeleted(0);
			Date date = new Date();
			requestCompany.setCompanyTimestamp(date.toString());
			companyRepository.save(CompanyBuilder(requestCompany));
			Company company = companyRepository.findAllByCompanyId(UUID1);
			return returnResponseBuilderCompany(company, "Company Added Successfully");
		} else {
			return (ResponseBuilderCompany.builder().message("Company Not Added").build());
		}
	}

	public ResponseBuilderCompany updateCompany(RequestCompany requestCompany, String id) {
		try {
			Company comp = companyRepository.findAllByCompanyId(id);
			Date date = new Date();
			requestCompany.setCompanyId(comp.getCompanyId());
			requestCompany.setCompanyTimestamp(date.toString());
			requestCompany.setDeleted(0);
			companyRepository.save(CompanyBuilder(requestCompany));
			Company company = companyRepository.findAllByCompanyId(id);
			return returnResponseBuilderCompany(company, "Company Updated Successfully");
		} catch (Exception e) {
			return (ResponseBuilderCompany.builder().message("Company with this ID Not Found").build());
		}
	}

	public String delCompany(String id) {
		try {
			Company company = companyRepository.findAllByCompanyId(id);
			company.setDeleted(1);
			companyRepository.save(company);
			return "Company Deleted Successfully";
		} catch (Exception e) {
			return "Company not found wrong CID";
		}

	}

	public List<Department> getdepartments(String id) {
		Company company = companyRepository.findAllByCompanyId(id);
		return company.getDeptList();
	}
	
	public List<Employee> getemployees(String id) {
		Company company = companyRepository.findAllByCompanyId(id);
		return company.getEmpList();
	}

	public Company getCompanyOG(String id) {
		Company company = companyRepository.findAllByCompanyId(id);
		return company;
	}


	public ResponseBuilderCompany returnResponseBuilderCompany(Company company, String message) {
		return (ResponseBuilderCompany.builder().companyId(company.getCompanyId()).companyName(company.getCompanyName())
				.companyAddress(company.getCompanyAddress()).companyCEO(company.getCompanyCEO())
				.companyContact(company.getCompanyContact()).companyTimestamp(company.getCompanyTimestamp())
				.companyYOE(company.getCompanyYOE()).deleted(company.getDeleted()).message(message).build());
	}

	public Company CompanyBuilder(RequestCompany requestCompany) {
		return (Company.builder().companyId(requestCompany.getCompanyId()).companyAddress(requestCompany.getCompanyAddress())
				.companyName(requestCompany.getCompanyName()).companyCEO(requestCompany.getCompanyCEO()).companyYOE(requestCompany.getCompanyYOE())
				.companyContact(requestCompany.getCompanyContact()).companyTimestamp(requestCompany.getCompanyTimestamp())
				.deleted(requestCompany.getDeleted()).build());
	}

}