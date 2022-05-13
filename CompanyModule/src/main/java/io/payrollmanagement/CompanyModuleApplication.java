package io.payrollmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;



@SpringBootApplication
@EnableDiscoveryClient
@PropertySource("classpath:application.properties")
public class CompanyModuleApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CompanyModuleApplication.class, args);}
	}