package com.test.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.test.test.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/employee")
    public ResponseEntity<JsonNode> getEmployees() throws JsonProcessingException {
        return employeeService.getEmployees();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<JsonNode> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }
}