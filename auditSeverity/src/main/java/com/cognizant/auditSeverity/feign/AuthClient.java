package com.cognizant.auditSeverity.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="audit-authorization-service",url="http://localhost:8080/authorization" )
public interface AuthClient {

	
	@GetMapping("/validate")
	public Boolean validate(@RequestHeader(name = "Authorization") String token1);
	
}

