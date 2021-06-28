package com.test.test.dto;

import java.util.List;

public class ResponseDTO {
    private String status;
    private List<EmployeeDTO> data;
    private String message;

    public List<EmployeeDTO> getData(){
        return this.data;
    }
}
