package com.test.test.service.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.dto.ResponseDTO;
import com.test.test.util.RestExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private RestExchange restExchange;

	@Override
	public ResponseEntity<JsonNode> getEmployees() throws JsonProcessingException {
		ResponseEntity<JsonNode> response = restExchange.exchange("http://dummy.restapiexample.com/api/v1/employees", null, HttpMethod.GET, JsonNode.class);
		return response.getStatusCode() == HttpStatus.OK ? response : null;
	}

	@Override
	public ResponseEntity<JsonNode> getEmployeeById(String id) {
		ResponseEntity<JsonNode> response = restExchange.exchange("http://dummy.restapiexample.com/api/v1/employee/" + id, null, HttpMethod.GET, JsonNode.class);
		return response.getStatusCode() == HttpStatus.OK ? response : null;
	}
}
