package io.payrollmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class EmployeeModuleApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(EmployeeModuleApplication.class, args);

	}

}