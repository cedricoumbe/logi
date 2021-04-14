package com.example.demo7.service;

import com.example.demo7.model.Employees;
import com.example.demo7.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

@Autowired
private EmployeeRepository employeeRepository;


    @Override
    public List<Employees> getAllEmployee() {

     return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employees employees) {

        this.employeeRepository.save(employees);

    }

    public Employees getEmployeeById(long id){

        Optional<Employees> optional = employeeRepository.findById(id);
        Employees employees = null;
        if(optional.isPresent()){
            employees = optional.get();
        }else{
            throw new RuntimeException("Employee not found for id ::"+ id);
        }
        return employees;
    }

    public void deleteEmployeeById(long id){

       this.employeeRepository.deleteById(id);
    }
}
