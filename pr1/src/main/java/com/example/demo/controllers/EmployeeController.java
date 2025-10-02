package com.example.demo.controllers;

import com.example.demo.classes.Employee;
import com.example.demo.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("user")
    public Employee getUserById(@RequestParam Long id){
    return employeeRepository.findById(id).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @GetMapping("users")
    public List<Employee> getAllUsers(){
        return employeeRepository.findAll();
    }

    @PostMapping("user")
    public ResponseEntity<Employee> addUser
            (@RequestParam(defaultValue ="null-name") String name,
             @RequestParam(defaultValue ="null-role") String role)
    {
        Employee e=new Employee();
        Employee e2=employeeRepository.save(e);
        e2.setName(name+e.getId());
        e2.setRole(role+e.getId());
        employeeRepository.save(e2);
        return ResponseEntity.status(HttpStatus.CREATED).body(e2);
    }



}
