package io.payrollmanagement.company;

import java.util.List;




import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Company {
	@Id
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
	
	@OneToMany
	private List<Employee> empList;
	

}