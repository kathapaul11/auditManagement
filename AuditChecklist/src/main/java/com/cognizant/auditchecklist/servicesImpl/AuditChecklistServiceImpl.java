package com.cognizant.auditchecklist.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.auditchecklist.exception.InvalidChoiceException;
import com.cognizant.auditchecklist.services.AuditChecklistService;

@Service
public class AuditChecklistServiceImpl implements AuditChecklistService{

	@Override
	public List<String> getChecklistQuestions(String auditType)throws InvalidChoiceException {
		
		List<String> questions =new ArrayList<String>();
		
		if (auditType.equals("Internal"))
		{
			questions.add("Have all Change requests followed SDLC before PROD move?");
			questions.add("Have all Change requests been approved by the application owner?");
			questions.add("Are all artifacts like CR document, Unit test cases available?");
			questions.add("Is the SIT and UAT sign-off available?");
			questions.add("Is data deletion from the system done with application owner approval?");
		}
		else if (auditType.equals("SOX"))
		{
			questions.add("Have all Change requests followed SDLC before PROD move?");
			questions.add("Have all Change requests been approved by the application owner?");
			questions.add("For a major change, was there a database backup taken before and after PROD move?");
			questions.add("Has the application owner approval obtained while adding a user to the system?");
			questions.add("Is data deletion from the system done with application owner approval?");
		}
		else {
			throw new InvalidChoiceException(auditType);
		}
		
		return questions;
	}

}
