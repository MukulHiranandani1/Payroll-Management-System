package io.payrollmanagement;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseBuilderCompany {
	private String companyId;
	private String companyName;
	private String companyAddress;
	private String companyContact;
	private String companyYOE;
	private String companyCEO;
	private String companyTimestamp;
	private int deleted;
}