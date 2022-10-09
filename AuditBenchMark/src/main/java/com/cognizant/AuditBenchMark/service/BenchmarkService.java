package com.cognizant.AuditBenchMark.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.AuditBenchMark.model.AuditBenchmark;

public interface BenchmarkService {

	public List<AuditBenchmark> auditBenchmark();
	
	
}
