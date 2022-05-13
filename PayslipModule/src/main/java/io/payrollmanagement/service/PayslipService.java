package io.payrollmanagement.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.payrollmanagement.FeignClient.FeignServicePayslip;
import io.payrollmanagement.payslip.Employee;
import io.payrollmanagement.payslip.Payslip;
import io.payrollmanagement.payslip.dto.RequestPayslip;
import io.payrollmanagement.repository.EmployeeRepository;
import io.payrollmanagement.repository.PayslipRepository;

@Service
public class PayslipService{


@Autowired
private PayslipRepository payslipRepository;
@Autowired
private EmployeeRepository employeeRepository;
@Autowired
private FeignServicePayslip PayslipFeign;
	

	public String updateSalary(RequestPayslip rP,String id,String Month,int year) {
		Employee e=PayslipFeign.getEmployeeOG(id);
		String MY=Month+Integer.toString(year);
		
		List<Payslip> paysliplist=e.getPaysliplist();
		for (int i = 0; i < paysliplist.size(); i++) {
			Payslip payslip=paysliplist.get(i);
			rP.setGrossDeductions(rP.getProfessionalTax()+rP.getProvidentFund()+rP.getIncomeTax());
			rP.setGrossEarnings(rP.getBasicSalary()+rP.getConveyance()+rP.getHRA());
			rP.setNetSalary(rP.getGrossEarnings()-rP.getGrossDeductions());
			if (payslip.getMonthandyear()==MY)
			{
			payslipRepository.save(Payslip.builder()
					.monthandyear(MY)
					.basicSalary(rP.getBasicSalary())
					.Conveyance(rP.getConveyance())
					.employee(e)
					.grossDeductions(rP.getGrossDeductions())
					.grossEarnings(rP.getGrossEarnings())
					.HRA(rP.getHRA())
					.incomeTax(rP.getIncomeTax())
					.netSalary(rP.getNetSalary())
					.professionalTax(rP.getProfessionalTax())
					.providentFund(rP.getProvidentFund())
					.build());
			}
		}
		return "Salary Updated";
		
	}

	public String addSalarySlip(RequestPayslip rP,String id,String Month,int year ) {
		Employee e=PayslipFeign.getEmployeeOG(id);
		String MY=Month+Integer.toString(year);
		

		rP.setGrossDeductions(rP.getProfessionalTax()+rP.getProvidentFund()+rP.getIncomeTax());
		rP.setGrossEarnings(rP.getBasicSalary()+rP.getConveyance()+rP.getHRA());
		rP.setNetSalary(rP.getGrossEarnings()-rP.getGrossDeductions());
		payslipRepository.save(Payslip.builder()
				.monthandyear(MY)
				.basicSalary(rP.getBasicSalary())
				.Conveyance(rP.getConveyance())
				.employee(e)
				.grossDeductions(rP.getGrossDeductions())
				.grossEarnings(rP.getGrossEarnings())
				.HRA(rP.getHRA())
				.incomeTax(rP.getIncomeTax())
				.netSalary(rP.getNetSalary())
				.professionalTax(rP.getProfessionalTax())
				.providentFund(rP.getProvidentFund())
				.build());
		List<Payslip> Payslips=e.getPaysliplist();
		Payslips.add(Payslip.builder()
				.monthandyear(MY)
				.basicSalary(rP.getBasicSalary())
				.Conveyance(rP.getConveyance())
				.employee(e)
				.grossDeductions(rP.getGrossDeductions())
				.grossEarnings(rP.getGrossEarnings())
				.HRA(rP.getHRA())
				.incomeTax(rP.getIncomeTax())
				.netSalary(rP.getNetSalary())
				.professionalTax(rP.getProfessionalTax())
				.providentFund(rP.getProvidentFund())
				.build());
		e.setPaysliplist(Payslips);
		employeeRepository.save(e);
		return "Slip added";
	}
	
	

}
