package com.cognizant.AuditBenchMark.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.AuditBenchMark.model.AuditBenchmark;
import com.cognizant.AuditBenchMark.service.BenchmarkService;

@Service
public class BenchmarkServiceImpl implements BenchmarkService{
	
	
	@Override
	public List<AuditBenchmark> auditBenchmark() {
		
		List<AuditBenchmark> obj = new ArrayList<>();
		
		AuditBenchmark internal = new AuditBenchmark("Internal" , 3);
		AuditBenchmark sox = new AuditBenchmark("SOX" , 1);
		
		obj.add(internal);
		obj.add(sox);
		
		return obj;
		
	}


}
