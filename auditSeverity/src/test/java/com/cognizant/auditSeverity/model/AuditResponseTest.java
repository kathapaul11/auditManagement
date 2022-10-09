package com.cognizant.auditSeverity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuditResponseTest {

	private static AuditResponse auditResponse;
	
	@BeforeAll
	static void init()
	{
		auditResponse=new AuditResponse(101,"RED","2 weeks","10-02-2022");
	}
	
	@Test
	void testGetters()
	{
		assertEquals(101, auditResponse.getAuditId());
		assertEquals("RED", auditResponse.getExecutionStatus());
		assertEquals("2 weeks", auditResponse.getActionDuration());
		assertEquals("10-02-2022", auditResponse.getDate());
		
	}
	
	@Test
	void testSetters()
	{
		auditResponse.setAuditId(102);
		auditResponse.setExecutionStatus("GREEN");
		auditResponse.setActionDuration("No action needed");
		auditResponse.setDate("11-02-2022");
		
		
		assertEquals(102, auditResponse.getAuditId());
		assertEquals("GREEN", auditResponse.getExecutionStatus());
		assertEquals("No action needed", auditResponse.getActionDuration());
		assertEquals("11-02-2022", auditResponse.getDate());
	}
}
