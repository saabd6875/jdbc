package com.example.jdbc_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcExampleApplication {

    @Autowired
    StudentRepository studentRepository;
    public static void main(String[] args) {
        SpringApplication.run(JdbcExampleApplication.class, args);
    }

}
