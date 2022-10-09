package com.cognizant.auditSeverity.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.auditSeverity.dao.AuditBenchmarkResponse;

@FeignClient(name="audit-benchmark-service" , url = "http://localhost:9092/auditBenchmark")

public interface AuditBenchmarkClient {

	@GetMapping("/getBenchmarks")
	public List<AuditBenchmarkResponse> auditBenchmark(@RequestHeader(name = "Authorization") String token1);
	
}
