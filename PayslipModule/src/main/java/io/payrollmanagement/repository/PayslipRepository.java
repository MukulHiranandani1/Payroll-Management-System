package io.payrollmanagement.repository;
import org.springframework.data.repository.CrudRepository;

import io.payrollmanagement.payslip.Payslip;

public interface PayslipRepository extends CrudRepository<Payslip, String>{}