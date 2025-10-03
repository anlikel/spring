package com.example.demo.controllers;

import com.example.demo.classes.Employee;
import com.example.demo.exceptions.NotFoundEmployeeException;
import com.example.demo.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("user/{id}")
    public Employee getUserById(@PathVariable Long id){
    return employeeRepository.findById(id).
            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @GetMapping("users")
    public List<Employee> getAllUsers(){
        return employeeRepository.findAll();
    }

    @PostMapping("user/{id}")
    public ResponseEntity<Employee> addUser
            (
             @RequestBody Employee employee)
    {
        Employee e=new Employee(employee.getName(), employee.getRole());
        Employee e2=employeeRepository.save(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(e2);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<Employee> updateUser(
            @PathVariable Long id,
            @RequestBody Employee employee){
        Employee old;
            old = employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundEmployeeException(id));
        old.setName(employee.getName());
        old.setRole(employee.getRole());
        Employee new2=employeeRepository.save(old);
        return ResponseEntity.ok(new2);
    }


}
