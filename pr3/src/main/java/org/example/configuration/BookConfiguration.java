package org.example.configuration;

import org.example.classes.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Configuration
public class BookConfiguration {

    @Autowired
    JdbcTemplate jd;

    BookingService bs;

    @Bean
    public String processBooking(BookingService bookingService) {
        bookingService.book("aa", "bb", "cc");
        bookingService.findAllBooks().forEach(System.out::println);
        return "Booking processing completed";
    }
}
