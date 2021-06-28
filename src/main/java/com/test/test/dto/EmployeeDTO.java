package com.test.test.dto;

public class EmployeeDTO {

    private String id;
    private String employee_name;
    private String employee_salary;
    private String employee_age;
    private String profile_image;
    private String employee_anual_salary;

    public void setEmployee_anual_salary(String salary){
        this.employee_anual_salary = salary;
    }

    public String getEmployee_salary(){
        return this.employee_salary;
    }
}
