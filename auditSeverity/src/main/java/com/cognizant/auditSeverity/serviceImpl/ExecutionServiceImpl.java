package com.cognizant.auditSeverity.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.auditSeverity.dao.AuditBenchmarkResponse;
import com.cognizant.auditSeverity.exception.InvalidChoiceException;
import com.cognizant.auditSeverity.feign.AuditBenchmarkClient;
import com.cognizant.auditSeverity.model.AuditDetail;
import com.cognizant.auditSeverity.model.AuditRequest;
import com.cognizant.auditSeverity.model.AuditResponse;
import com.cognizant.auditSeverity.repository.ExecutionStatusRepo;
import com.cognizant.auditSeverity.service.ExecutionService;

@Service
public class ExecutionServiceImpl implements ExecutionService {

	public ExecutionServiceImpl(AuditBenchmarkClient c, ExecutionStatusRepo r) {
		this.client = c;
		this.repo = r;
	}

	public ExecutionServiceImpl() {
	}

	@Autowired
	private ExecutionStatusRepo repo;

	@Autowired
	private AuditBenchmarkClient client;

	@Override
	public AuditResponse projectExecutionStatus(String token, AuditRequest auditRequest) throws InvalidChoiceException {

		AuditDetail auditDetail = new AuditDetail();
		auditDetail = auditRequest.getAuditDetail();

		System.out.println(auditRequest);

		AuditResponse auditResponse = new AuditResponse();

		List<AuditBenchmarkResponse> auditBenchmark = this.client.auditBenchmark(token);

		String auditType = auditRequest.getAuditDetail().getAuditType();
		if (auditType.equals("Internal") || auditType.equals("SOX")) {

			for (AuditBenchmarkResponse it : auditBenchmark) {
				if (auditType.equals(it.getAuditType())) {
					if (auditRequest.getAuditDetail().getQuestions() > it.getNoOfNoAnswers()) {
						auditResponse.setExecutionStatus("Red");
						auditResponse.setDate(auditRequest.getAuditDetail().getDate());
						if (auditType.equals("Internal")) {
							auditResponse.setActionDuration("2 weeks");
						} else {
							auditResponse.setActionDuration("1 week");
						}
					} else {

						auditResponse.setExecutionStatus("Green");
						auditResponse.setActionDuration("No Action Needed");
						auditResponse.setDate(auditRequest.getAuditDetail().getDate());
					}
				}
			}
		} else {
			throw new InvalidChoiceException("Invalid Audit Type Choosen...");
		}

		this.repo.save(auditResponse);
		System.out.println(auditResponse);
		return auditResponse;
	}

}
