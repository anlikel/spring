package org.example.classes;


import jakarta.persistence.*;

import java.lang.reflect.Type;

@Entity
@Table(name="BOOKINGS")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(name="FIRST_NAME")
    String firstName;

    protected Book (){};

    public Book(String firstName) { // Конструктор для создания объектов
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
