package com.example.demo7.service;

import com.example.demo7.model.Employees;

import java.util.List;

public interface EmployeeService {

    List<Employees> getAllEmployee();
    void saveEmployee(Employees employee);
    Employees getEmployeeById(long id);
    void deleteEmployeeById(long id);
}
