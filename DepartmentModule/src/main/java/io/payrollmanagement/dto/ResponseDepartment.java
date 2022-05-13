package io.payrollmanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDepartment {
	
	private String departmentId;
	private String departmentName;
	private String departmentHead;
	private String departmentTimestamp;
	private int deleted;
	private String message;
}
