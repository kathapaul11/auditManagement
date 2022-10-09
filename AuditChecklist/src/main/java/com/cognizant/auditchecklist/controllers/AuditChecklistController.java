package com.cognizant.auditchecklist.controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auditchecklist.exception.InvalidChoiceException;
import com.cognizant.auditchecklist.feign.AuthClient;
import com.cognizant.auditchecklist.services.AuditChecklistService;

@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RestController
@RequestMapping("/auditChecklist")
public class AuditChecklistController {
	
	@Autowired
	private AuditChecklistService service;
	
	@Autowired
	private AuthClient client;
	
	@GetMapping("/{auditType}")
	public ResponseEntity<List<String>> AuditCheckListQuestions(@RequestHeader(name = "Authorization") String token,@PathVariable String auditType)
	{
		try {
			if(client.validate(token)) {
				System.out.println("In audit checklist controller!!!");
				return new ResponseEntity<>(service.getChecklistQuestions(auditType),HttpStatus.OK);
			} 
		}
		catch (InvalidChoiceException e) {
			e.getMessage();
			return new ResponseEntity<>(HttpStatus.FORBIDDEN); 
		}
		
	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
	}

}
