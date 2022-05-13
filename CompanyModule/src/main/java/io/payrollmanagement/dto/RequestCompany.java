package io.payrollmanagement.dto;
import java.util.List;

import javax.persistence.OneToMany;

import io.payrollmanagement.company.Department;
import lombok.Data;

@Data
public class RequestCompany {
	
	
	private String companyId;
	private String companyName;
	private String companyAddress;
	private String companyContact;
	private String companyYOE;
	private String companyCEO;
	private String companyTimestamp;
	private int deleted;
	@OneToMany
	private List<Department> deptList;
}