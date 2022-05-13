package io.payrollmanagement.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.payrollmanagement.payslip.dto.RequestPayslip;
import io.payrollmanagement.service.PayslipService;

	
	@RestController
	public class PayslipController {
		@Autowired
		private PayslipService payslipService;
 /*@RequestMapping("/{id}/{monthSalary}/{yearSalary}")
	public List<ResponseBuilder> getSalary(){
		return payslipService.getSalary();
	}*/
		Logger logger=LoggerFactory.getLogger(PayslipController.class);	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/{month}/{year}")
	public String addSalarySlip(@RequestBody RequestPayslip rP, @PathVariable String id, @PathVariable String month, @PathVariable int year){
			 return payslipService.addSalarySlip(rP,id,month,year);
		}

		
 @RequestMapping(method=RequestMethod.PUT, value="/{id}/{month}/{year}")
 public String updateSalary(@RequestBody RequestPayslip rP, @PathVariable String id, @PathVariable String month, @PathVariable int year){
	 return payslipService.updateSalary(rP,id,month,year);
 }
		
	}
