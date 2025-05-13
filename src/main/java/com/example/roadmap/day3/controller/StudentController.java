package com.example.roadmap.day3.controller;

import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.day3.service.StudentService;
import com.example.roadmap.day3.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentValidation validation;

    @GetMapping("/{studentId}")
    public ResponseWrapper getStudent(@PathVariable Integer studentId){
        validation.notEmpty(studentId);
        Student student = studentService.getStudent(studentId);
        return studentService.convertStudentResponse(student);
    }

    @PostMapping("/create")
    public ResponseWrapper createStudent(@RequestBody Student student){
        validation.validateStudentObject(student);
        return new ResponseWrapper(201,studentService.createStudent(student));
    }

    @PutMapping("/update")
    public ResponseWrapper updateStudent(@RequestBody Student student){
        validation.validateStudentObject(student);
        return new ResponseWrapper(201,studentService.updateStudent(student));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseWrapper deleteStudent(@PathVariable Integer studentId) {
        validation.notEmpty(studentId);
        studentService.deleteStudent(studentId);
        return new ResponseWrapper(204,studentId);
    }

}
