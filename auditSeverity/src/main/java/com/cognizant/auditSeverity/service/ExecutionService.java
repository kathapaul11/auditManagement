package com.cognizant.auditSeverity.service;

import com.cognizant.auditSeverity.exception.InvalidChoiceException;
import com.cognizant.auditSeverity.model.AuditRequest;
import com.cognizant.auditSeverity.model.AuditResponse;

public interface ExecutionService {

	public AuditResponse projectExecutionStatus(String token,AuditRequest auditRequest) throws InvalidChoiceException;
	
}
