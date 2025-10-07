package org.example.controllers;

import org.example.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getLong("id"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            return customer;
        }
    }

    @GetMapping("/customers/{id}")
    public List<Customer>findByIds(@PathVariable String id){
        String sql = "SELECT * FROM customers WHERE id = '" + id + "'";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }
}
