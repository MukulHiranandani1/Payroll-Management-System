package io.payrollmanagement.FeignClient;
import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.payrollmanagement.payslip.Employee;
import io.payrollmanagement.payslip.Payslip;





@FeignClient(name="FeignClient4",url="http://localhost:8083")
public interface FeignServicePayslip {

	@RequestMapping(method=RequestMethod.GET,value="/employees/og/{id}",consumes="application/json")
	public Employee getEmployeeOG(@PathVariable ("id") String id);
	
	@RequestMapping(method=RequestMethod.GET,value="/employees/{id}/salaryslips",consumes="application/json")
	public List<Payslip> getsalarySlips(@PathVariable ("id") String id);
}

