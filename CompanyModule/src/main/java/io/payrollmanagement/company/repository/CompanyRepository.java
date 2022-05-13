package io.payrollmanagement.company.repository;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;

//import java.util.Optional;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.payrollmanagement.company.Company;


public interface CompanyRepository extends CrudRepository<Company, String> {
	Company findAllByCompanyId(String id);

	/*
	 * @Query(nativeQuery = true, value =
	 * "SELECT * FROM Company WHERE Company.deleted= false") List<Company>
	 * getActiveCompany();
	 */

	List<Company> findAllByDeleted(int d);
	

}