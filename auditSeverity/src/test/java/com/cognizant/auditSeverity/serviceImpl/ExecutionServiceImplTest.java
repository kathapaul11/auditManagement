package com.cognizant.auditSeverity.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cognizant.auditSeverity.dao.AuditBenchmarkResponse;
import com.cognizant.auditSeverity.feign.AuditBenchmarkClient;
import com.cognizant.auditSeverity.model.AuditDetail;
import com.cognizant.auditSeverity.model.AuditRequest;
import com.cognizant.auditSeverity.model.AuditResponse;
import com.cognizant.auditSeverity.repository.ExecutionStatusRepo;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionServiceImplTest {

	@Mock
	static ExecutionStatusRepo repo;
	
	@Mock
	static AuditBenchmarkClient client;
	
	@InjectMocks
	static ExecutionServiceImpl executionServiceImpl;
	
	private static AuditDetail auditDetail =new AuditDetail();
	
	private static AuditRequest auditRequest;
	
	private static AuditResponse auditResponse;
	
	
	@BeforeAll
	static void init()
	{
		List<AuditBenchmarkResponse> li =new ArrayList<AuditBenchmarkResponse>();
		li.add(new AuditBenchmarkResponse("Internal",5));
		client=mock(AuditBenchmarkClient.class);
		repo=mock(ExecutionStatusRepo.class);
		executionServiceImpl=new ExecutionServiceImpl(client,repo);
		when(client.auditBenchmark("")).thenReturn(li);		
		auditDetail.setAuditType("Internal");
		auditDetail.setDate("12/12/2021");
		auditDetail.setQuestions(4);
	
		auditRequest=new AuditRequest("","","",auditDetail);
	}
	
	@Test
	void testProjectExecutionStatus() throws Exception
	{
		
		auditResponse = executionServiceImpl.projectExecutionStatus("",auditRequest);
		assertEquals("Green", auditResponse.getExecutionStatus());
		assertEquals("No Action Needed", auditResponse.getActionDuration());
	}
}
