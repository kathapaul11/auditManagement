package com.cognizant.AuditBenchMark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@AllArgsConstructor
@NoArgsConstructor
public class AuditBenchmark {

	private String auditType;
	private int noOfNoAnswers;	

}
