package com.example.demo.controllers;

import com.example.demo.classes.Employee;
import com.example.demo.classes.EmployeeModelAssembler;
import com.example.demo.exceptions.NotFoundEmployeeException;
import com.example.demo.repositores.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    public ResponseEntity<?> addUser
            (
             @RequestBody Employee employee)
    {
        EntityModel<Employee>e=employeeModelAssembler.toModel(employeeRepository.save(employee));
        return ResponseEntity.created(e.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(e);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody Employee employee){
        Employee old;
            old = employeeRepository.findById(id)
                    .orElseThrow(() -> new NotFoundEmployeeException(id));
        old.setName(employee.getName());
        old.setRole(employee.getRole());
        Employee new2=employeeRepository.save(old);

        EntityModel<Employee>e=employeeModelAssembler.toModel(new2);
        return ResponseEntity.created(e.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(e);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUser
            (
                    @PathVariable Long id)
    {
        employeeRepository.deleteById(id);

//        return ResponseEntity.ok().body("deleted");
        return ResponseEntity.noContent().build();
    }

}
