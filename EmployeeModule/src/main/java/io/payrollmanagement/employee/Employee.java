package io.payrollmanagement.employee;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="departmentId")
	Department dept;
	
	@OneToMany
	List<Payslip> Paysliplist;
}