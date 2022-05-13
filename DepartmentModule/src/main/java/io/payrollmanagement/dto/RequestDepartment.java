package io.payrollmanagement.dto;

import lombok.Data;

@Data
public class RequestDepartment {
	
	private String departmentId;
	private String departmentName;
	private String departmentHead;
	private String departmentTimestamp;
	private int deleted;
	
}
