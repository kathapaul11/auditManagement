package com.cognizant.AuditBenchMark;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.AuditBenchMark.controller.AuditBenchmarkController;
import com.cognizant.AuditBenchMark.feign.AuthClient;
import com.cognizant.AuditBenchMark.model.AuditBenchmark;
import com.cognizant.AuditBenchMark.serviceImpl.BenchmarkServiceImpl;


@SpringBootTest
@RunWith(SpringRunner.class)
class AuditBenchMarkApplicationTests {

	@Autowired
	BenchmarkServiceImpl benchmarkServiceImpl;
	
	@Autowired
	AuditBenchmarkController controller;
	
	@MockBean
	AuthClient authClient;
	
	@Test
	public void testBenchmarkServiceImpl() {
		
		List<AuditBenchmark> audits = benchmarkServiceImpl.auditBenchmark();
		
		assertEquals(audits.get(0), new AuditBenchmark("Internal",3));
		assertEquals(audits.get(1), new AuditBenchmark("SOX",1));
	}
	

	@Test
	public void testBenchMarkController() {
		
		String token ="token1";
		when(authClient.validate("token1")).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));
		ResponseEntity<List<AuditBenchmark>> audit = controller.auditBenchmark(token);
		assertEquals(audit.getStatusCodeValue(), 200 );
		
	}

}
