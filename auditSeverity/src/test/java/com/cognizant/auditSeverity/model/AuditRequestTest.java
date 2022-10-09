package com.cognizant.auditSeverity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AuditRequestTest {

	@Mock
	private static AuditDetail auditDetail;
	
	@InjectMocks
	private static AuditRequest auditRequest;
	
	@BeforeAll
	static void init()
	{
		auditRequest =new AuditRequest("Project name","Mr. Abc","Mr. XYZ",auditDetail);
	}
	
	@Test
	void testGetters()
	{
		assertEquals("Project name", auditRequest.getProjectName());
		assertEquals("Mr. Abc", auditRequest.getProjectManagerName());
		assertEquals("Mr. XYZ", auditRequest.getAppOwnerName());
		assertEquals(auditDetail,auditRequest.getAuditDetail());
	}
	
	@Test
	void testSetters()
	{
		auditRequest.setProjectName("project");
		auditRequest.setProjectManagerName("Abc");
		auditRequest.setAppOwnerName("XYZ");
		auditRequest.setAuditDetail(auditDetail);
		
		assertEquals("project", auditRequest.getProjectName());
		assertEquals("Abc", auditRequest.getProjectManagerName());
		assertEquals("XYZ", auditRequest.getAppOwnerName());
		assertEquals(auditDetail,auditRequest.getAuditDetail());
	}
}
