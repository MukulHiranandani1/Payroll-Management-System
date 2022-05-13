package io.payrollmanagement;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.payrollmanagement.employee.Company;
import io.payrollmanagement.employee.Employee;



@FeignClient(name="FeignClient",url="http://localhost:8081")
public interface FeignServiceCompany {

	 @RequestMapping(method=RequestMethod.GET,value="/companies/{id}",consumes="application/json")
	  public ResponseBuilderCompany getCompany(@PathVariable("id") String id);
	 

	 @RequestMapping(method=RequestMethod.GET,value="/companies/og/{id}",consumes="application/json")
	  public Company getCompanyOG(@PathVariable("id") String id);
	 
	 @RequestMapping(method=RequestMethod.GET,value="/companies/{id}/employees",consumes="application/json")
	  public List<Employee> getemployees(@PathVariable("id") String id);
	 
}
