package com.test.test.service.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

	ResponseEntity<JsonNode> getEmployees() throws JsonProcessingException;
	ResponseEntity<JsonNode> getEmployeeById(String id);
}
