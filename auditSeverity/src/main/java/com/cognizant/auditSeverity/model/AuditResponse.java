package com.cognizant.auditSeverity.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuditResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int auditId;
	private String executionStatus;
	private String actionDuration;
	private String Date;

}
