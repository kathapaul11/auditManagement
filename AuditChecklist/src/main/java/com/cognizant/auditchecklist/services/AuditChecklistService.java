package com.cognizant.auditchecklist.services;

import java.util.List;

import com.cognizant.auditchecklist.exception.InvalidChoiceException;



public interface AuditChecklistService{
	
	
	public List<String> getChecklistQuestions(String auditType)throws InvalidChoiceException;

}
