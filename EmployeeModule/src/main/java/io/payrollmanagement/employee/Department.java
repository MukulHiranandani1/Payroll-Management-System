package io.payrollmanagement.employee;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Department{
       
	    @Id
		private String departmentId;
		private String departmentName;
		private String departmentHead;
		private String departmentTimestamp;
		private int deleted;
		
		@ManyToOne
		@JoinColumn(name="companyId")
		Company company;
		
		@OneToMany
		private List<Employee> empList;
}
