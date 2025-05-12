package com.example.roadmap.day3.controller;

import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.repository.StudentRepository;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.day3.service.StudentService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseWrapper getStudent(@PathVariable Integer studentId){
        Student student = studentService.getStudent(studentId);
        return studentService.convertStudentResponse(student);
    }

    @PostMapping("/create")
    public Integer createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping("/update")
    public Integer updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
    }

}
