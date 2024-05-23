package com.example.jdbc_example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private String name;
    private Integer age;
    private String university;
    private Long id;


    public Student(String name, Integer age, String university) {
        this.name = name;
        this.age = age;
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", university='" + university + '\'' +
                '}';
    }
}

