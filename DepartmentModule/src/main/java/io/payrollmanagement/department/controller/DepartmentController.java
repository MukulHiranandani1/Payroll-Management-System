 package io.payrollmanagement.department.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.payrollmanagement.dto.RequestDepartment;
import io.payrollmanagement.dto.ResponseDepartment;
import io.payrollmanagement.service.DepartmentService;

@RestController
public class DepartmentController  {
		@Autowired
		private DepartmentService departmentService;
		Logger logger=LoggerFactory.getLogger(DepartmentController.class);
		@RequestMapping("/departments/all")
		public List<ResponseDepartment> getalldepartments()
		{
			return departmentService.getAllDepartment();
		}
		
		@RequestMapping("/departments/{id}")
		public ResponseDepartment getDepartment(@PathVariable String id) {
			return departmentService.getDepartment(id);
		}
		
		@RequestMapping(method=RequestMethod.POST,value="/departments/{id}")
		public ResponseDepartment addDepartment(@PathVariable String id,@RequestBody RequestDepartment R)
		{
			return departmentService.addDepartment(id,R);
		}
		
		@RequestMapping(method=RequestMethod.PUT,value="/departments/{id}")
		public ResponseDepartment updateDepartment(@RequestBody RequestDepartment R,@PathVariable String id)
		{
			return departmentService.updateDepartment(R,id);
		}
		
		
		@RequestMapping(method=RequestMethod.DELETE,value="/departments/{id}")
		public String delDepartment(@PathVariable String id) {
			return departmentService.delDepartment(id);
		}
		@RequestMapping("/activedepartments")
		public List<ResponseDepartment> getActiveDepartment() {
		return departmentService.getActiveDepartment();
		}//Isko update kro bc
		
}

