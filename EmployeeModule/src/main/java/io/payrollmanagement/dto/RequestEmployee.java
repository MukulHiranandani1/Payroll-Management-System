package io.payrollmanagement.dto;

import lombok.Data;

@Data
public class RequestEmployee {

	private String employeeId;
	private String employeeName;
	private String employeeContact;
	private String employeeAddress;
	private String employeeDept;
	private String employeeTimestamp;
	private int deleted;
	private String employeeDesign;
}
