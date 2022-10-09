package com.cognizant.auditSeverity.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="audit-checklist-service" , url = "http://localhost:9091/auditChecklist")
public interface AuditChecklistClient {

}
