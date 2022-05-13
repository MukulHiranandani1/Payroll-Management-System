package io.payrollmanagement.payslip;





import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Payslip {

	
	@Id
	private String monthandyear;
		private double basicSalary;
		private double HRA;
		private double Conveyance;
		private double providentFund;
		private double professionalTax;
		private double incomeTax;
		private double grossEarnings;
		private double grossDeductions;
		private double netSalary;

		//@JsonIgnore
		@ManyToOne
		Employee employee;
	}
