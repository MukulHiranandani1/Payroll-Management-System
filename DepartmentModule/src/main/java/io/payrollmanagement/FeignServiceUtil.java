package io.payrollmanagement;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import io.payrollmanagement.department.Department;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.payrollmanagement.department.Company;



@FeignClient(name="FeignClient",url="http://localhost:8081")
public interface FeignServiceUtil {

	 @RequestMapping(method=RequestMethod.GET,value="/companies/{id}",consumes="application/json")
	  public ResponseBuilderCompany getCompany(@PathVariable ("id")String id);
	 
	 @RequestMapping(method=RequestMethod.GET,value="/companies/og/{id}",consumes="application/json")
	  public Company getCompanyOG(@PathVariable ("id") String id);
	 
	 @RequestMapping(method=RequestMethod.GET,value="/companies/{id}/departments",consumes="application/json")
	  public List<Department> getdepartments(@PathVariable("id") String id);
	 
}
