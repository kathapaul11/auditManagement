package com.cognizant.AuditBenchMark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuditBenchMarkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditBenchMarkApplication.class, args);
	}

}
