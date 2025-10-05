package com.example.demo.controllers;

import com.example.demo.classes.Employee;
import com.example.demo.classes.EmployeeModelAssembler;
import com.example.demo.exceptions.NotFoundEmployeeException;
import com.example.demo.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    public EmployeeController(EmployeeRepository employeeRepository,EmployeeModelAssembler employeeModelAssembler){
        this.employeeRepository=employeeRepository;
        this.employeeModelAssembler=employeeModelAssembler;
    }
//    @GetMapping("user/{id}")
//    public Employee getUserById(@PathVariable Long id){
//    return employeeRepository.findById(id).
//            orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
//    }

    @GetMapping("/user/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id) //
                .orElseThrow(() -> new NotFoundEmployeeException(id));

        return employeeModelAssembler.toModel(employee);
    }

    @GetMapping("users")
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>>list=employeeRepository.findAll().stream()
                .map(employeeModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(list,linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
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

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> addUser
            (
                    @PathVariable Long id)
    {
        employeeRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
