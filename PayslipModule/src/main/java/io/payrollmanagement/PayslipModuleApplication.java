package io.payrollmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PayslipModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayslipModuleApplication.class, args);
	}

}
