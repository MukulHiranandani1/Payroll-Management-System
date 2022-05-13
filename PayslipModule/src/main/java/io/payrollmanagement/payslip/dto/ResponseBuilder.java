package io.payrollmanagement.payslip.dto;

import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseBuilder {
	@Id
	private String SlipID;
	private String month;
	private int year;
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
