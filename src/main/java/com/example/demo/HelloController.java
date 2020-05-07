package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping(value = "/emp/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id") final int id1) {
        final Employee employee = employeeService.getEmployee(id1);
        return ResponseEntity.ok().body(employee);
    }

}
