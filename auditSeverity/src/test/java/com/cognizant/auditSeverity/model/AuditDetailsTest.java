package com.cognizant.auditSeverity.model;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuditDetailsTest {
	
	private static AuditDetail auditDetail;
	
	@BeforeAll
	static void init()
	{
		auditDetail = new AuditDetail("Internal","12/12/2021",10);
		
	}
	
	@Test
	public void testGetter()
	{
		assertEquals("Internal", auditDetail.getAuditType());
		//assertEquals(new Date(), auditDetail.getDate());
		assertEquals(10, auditDetail.getQuestions());
	}
	@Test
	public void testSetter()
	{
		auditDetail.setAuditType("Internal");
		//auditDetail.setDate(new Date());
		auditDetail.setQuestions(10);
		
		assertEquals("Internal", auditDetail.getAuditType());
		//assertEquals(new Date(), auditDetail.getDate());
		assertEquals(10, auditDetail.getQuestions());
	}

}
