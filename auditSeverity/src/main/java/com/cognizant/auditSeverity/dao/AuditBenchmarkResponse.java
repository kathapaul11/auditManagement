package com.cognizant.auditSeverity.dao;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuditBenchmarkResponse {
	
	private String auditType;
	private int noOfNoAnswers;

}
