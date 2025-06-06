package com.example.roadmap.day3.controller;

import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.request.RequestWrapper;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.day3.service.StudentService;
import com.example.roadmap.day3.validation.StudentValidation;
import com.example.roadmap.s2s.NotificationFeignUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Autowired
    private StudentService studentService;

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

    @Autowired
    private NotificationFeignUtil notificationFeignUtil;

    @Autowired
    private StudentValidation validation;

    @GetMapping("/{studentId}")
    public ResponseWrapper getStudent(@PathVariable Integer studentId){
        validation.notEmpty(studentId);
        Student student = studentService.getStudent(studentId);
        return studentService.convertStudentResponse(student);
    }

    @PostMapping("/create")
    public ResponseWrapper createStudent(@RequestBody RequestWrapper requestWrapper){
        validation.validateStudentObject(requestWrapper);
        Integer student = studentService.createStudent(requestWrapper);
        notificationFeignUtil.captureStudentId(student);
//        kafkaTemplate.send("NewTopic",String.valueOf(student));
        return new ResponseWrapper(201,student);
    }

    @PutMapping("/update")
    public ResponseWrapper updateStudent(@RequestBody RequestWrapper requestWrapper){
        validation.validateStudentObject(requestWrapper);
        return new ResponseWrapper(201,studentService.updateStudent(requestWrapper));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseWrapper deleteStudent(@PathVariable Integer studentId) {
        validation.notEmpty(studentId);
        studentService.deleteStudent(studentId);
        return new ResponseWrapper(204,studentId);
    }

    @PostMapping("/upload")
    public String importStudentDetails(@RequestPart(value = "file") MultipartFile file){
        return studentService.uploadFile(file);
    }

}
