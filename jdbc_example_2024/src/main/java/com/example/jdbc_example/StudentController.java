package com.example.jdbc_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @GetMapping("/getStudentsFromDB")
    public Student getStudentsFromDB(@RequestParam Long id){
        return studentRepository.findById(id);
    }

    @GetMapping("/insertStudent")
    public void insertStudent(){
        studentRepository.insertStudent(new Student("John",23,"Oslomet"));
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(){
        return  studentRepository.findAll();
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long id){
        studentRepository.deleteStudent(id);
        return "deleted";
    }

    @PostMapping("/insertStudentInDB")
    public void insertStudentInDb(Student student){
        studentRepository.insertStudent(student);
    }

    @PostMapping("/updateStudent")
    public String updateStudentDBPost(Student student){
//        Student stud = studentRepository.findById(student.getId());
//        stud.setName(student.getName());
//        stud.setUniversity(student.getUniversity());
//        stud.setAge(student.getAge());
        studentRepository.updateStudent(student);
        return "updated";
    }
}
