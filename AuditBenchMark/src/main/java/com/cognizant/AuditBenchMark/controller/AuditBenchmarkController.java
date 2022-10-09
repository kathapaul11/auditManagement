package com.cognizant.AuditBenchMark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.AuditBenchMark.feign.AuthClient;
import com.cognizant.AuditBenchMark.model.AuditBenchmark;
import com.cognizant.AuditBenchMark.service.BenchmarkService;
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping("/auditBenchmark")
public class AuditBenchmarkController {
	
	@Autowired
	private BenchmarkService service;
	
	@Autowired
	private AuthClient client;
	

	@GetMapping("/getBenchmarks")
	public ResponseEntity<List<AuditBenchmark>> auditBenchmark(@RequestHeader(name = "Authorization") String token) {
		
		if(client.validate(token) != null) {
			return new ResponseEntity<List<AuditBenchmark>>(this.service.auditBenchmark(),HttpStatus.OK);
		}
	  return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	  
	  
		}
		
	
	
}
