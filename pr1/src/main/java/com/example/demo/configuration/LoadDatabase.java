package com.example.demo.configuration;

import com.example.demo.classes.Employee;
import com.example.demo.classes.Order;
import com.example.demo.enums.Status;
import com.example.demo.repositores.EmployeeRepository;
import com.example.demo.repositores.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log= LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initEmployeeDatabase(EmployeeRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo","Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo","Baggins", "thief")));
        };
    }

    @Bean
    public CommandLineRunner initOrderDatabase(OrderRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Order("1 order", Status.IN_PROGRESS)));
            log.info("Preloading " + repository.save(new Order("2 order", Status.CANCELLED)));
            log.info("Preloading " + repository.save(new Order("3 order", Status.COMPLETED)));
        };
    }
}
