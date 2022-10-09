package com.cognizant.auditSeverity.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auditSeverity.feign.AuthClient;
import com.cognizant.auditSeverity.model.AuditRequest;
import com.cognizant.auditSeverity.model.AuditResponse;
import com.cognizant.auditSeverity.service.ExecutionService;
import com.cognizant.auditSeverity.exception.InvalidChoiceException;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping("/severity")
public class AuditSeverityController {
	
	@Autowired
	private ExecutionService service;
	
	@Autowired
	private AuthClient client;
	
	@PostMapping("/auditStatus")
	public ResponseEntity<AuditResponse> projectExecutionStatus(@RequestHeader(name = "Authorization") String token,@RequestBody AuditRequest auditRequest) {
		try {
		
		if(client.validate(token)) {
			return new ResponseEntity<>(this.service.projectExecutionStatus(token,auditRequest),HttpStatus.OK);
		} 
		} 
		catch (InvalidChoiceException e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
