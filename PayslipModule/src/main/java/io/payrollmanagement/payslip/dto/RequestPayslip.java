package io.payrollmanagement.payslip.dto;

import javax.persistence.Id;

import lombok.Data;
@Data
public class RequestPayslip {
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

		}

