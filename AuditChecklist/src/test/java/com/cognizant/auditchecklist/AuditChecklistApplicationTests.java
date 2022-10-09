package com.cognizant.auditchecklist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.auditchecklist.controllers.AuditChecklistController;
import com.cognizant.auditchecklist.exception.InvalidChoiceException;
import com.cognizant.auditchecklist.feign.AuthClient;
import com.cognizant.auditchecklist.servicesImpl.AuditChecklistServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class AuditChecklistApplicationTests {
	
	@Autowired
	AuditChecklistServiceImpl service;
	
	@Autowired
	AuditChecklistController controller;
	
	@MockBean
	AuthClient authClient;
	
	static List<String> expected1 = new ArrayList<String>();
	static List<String> expected2=new ArrayList<String>();
	
	@BeforeAll
	public static void init() {
		
		expected1.add("Have all Change requests followed SDLC before PROD move?");
		expected1.add("Have all Change requests been approved by the application owner?");
		expected1.add("Are all artifacts like CR document, Unit test cases available?");
		expected1.add("Is the SIT and UAT sign-off available?");
		expected1.add("Is data deletion from the system done with application owner approval?");
		
		expected2.add("Have all Change requests followed SDLC before PROD move?");
		expected2.add("Have all Change requests been approved by the application owner?");
		expected2.add("For a major change, was there a database backup taken before and after PROD move?");
		expected2.add("Has the application owner approval obtained while adding a user to the system?");
		expected2.add("Is data deletion from the system done with application owner approval?");

	}
	
	@Test
	void testGetChecklistQuestionsWhenAuditTypeIsInternal()throws InvalidChoiceException
	{
		List<String> actual = service.getChecklistQuestions("Internal");
		assertArrayEquals( expected1.toArray(), actual.toArray());
	}
	
	@Test
	void testGetChecklistQuestionsWhenAuditTypeIsSOX()throws InvalidChoiceException
	{
		List<String> actual = service.getChecklistQuestions("SOX");
		assertArrayEquals( expected2.toArray(),actual.toArray());
	}
	
	@Test
	public void testAuditChecklistControllerWithTypeInternal() {
		
		String token ="token1";
		when(authClient.validate("token1")).thenReturn(true);
		ResponseEntity<List<String>> actual = controller.AuditCheckListQuestions(token,"Internal");
		assertArrayEquals( expected1.toArray(), actual.getBody().toArray());
		assertEquals(200, actual.getStatusCodeValue());
		
	}
	
	@Test
	public void testAuditChecklistControllerWithTypeSOX() {
		
		String token ="token1";
		when(authClient.validate("token1")).thenReturn(true);
		ResponseEntity<List<String>> actual = controller.AuditCheckListQuestions(token,"SOX");
		assertArrayEquals( expected2.toArray(), actual.getBody().toArray());
		assertEquals(200, actual.getStatusCodeValue());
		
	}

}
