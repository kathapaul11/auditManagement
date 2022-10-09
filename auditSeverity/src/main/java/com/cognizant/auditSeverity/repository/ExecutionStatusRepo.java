package com.cognizant.auditSeverity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.auditSeverity.model.AuditResponse;

public interface ExecutionStatusRepo extends JpaRepository<AuditResponse, Integer>{

}
