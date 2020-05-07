package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public Employee getEmployee(int id) {
        return new Employee(id, "tej", new EmpAddress());
    }
}
