package io.payrollmanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBuilderEmployee {

	private String employeeId;
	private String employeeName;
	private String employeeContact;
	private String employeeAddress;
	private String employeeDept;
	private String employeeTimestamp;
	private int deleted;
	private String employeeDesign;
	private String message;
}
