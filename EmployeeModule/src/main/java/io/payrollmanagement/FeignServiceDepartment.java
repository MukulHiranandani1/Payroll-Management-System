package io.payrollmanagement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient(name="FeignClient2",url="http://localhost:8082")
public interface FeignServiceDepartment {

	 @RequestMapping(method=RequestMethod.GET,value="/departments/{id}",consumes="application/json")
	  public ResponseBuilderDepartment getDepartment(@PathVariable String id);
	 
}
