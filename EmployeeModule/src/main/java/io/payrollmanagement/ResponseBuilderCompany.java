package io.payrollmanagement;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseBuilderCompany {
	private String cid;
	private String company_name;
	private String company_address;
	private String company_contact;
	private String company_yoe;
	private String company_ceo;
	private String company_timestamp;
	private int deleted;
}