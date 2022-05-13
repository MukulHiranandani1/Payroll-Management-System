package io.payrollmanagement.department;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	private String employeeContact;
	private String employeeAddress;
	private String employeeDept;
	private String employeeTimestamp;
	private int deleted;
	private String employeeDesign;

	@ManyToOne
	@JoinColumn(name="companyId")
	Company company;
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	Department dept;
	
}