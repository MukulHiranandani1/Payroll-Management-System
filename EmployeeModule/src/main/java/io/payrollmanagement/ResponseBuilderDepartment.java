package io.payrollmanagement;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBuilderDepartment {
	
	private String did;
	private String department_name;
	private String department_HOD;
	private String department_timeStamp;
	private int deleted;
	
}
