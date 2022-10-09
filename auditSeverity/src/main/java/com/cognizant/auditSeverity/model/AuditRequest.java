package com.cognizant.auditSeverity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditRequest {

	private String projectName;
	private String projectManagerName;
	private String appOwnerName;
	private AuditDetail auditDetail;

}
