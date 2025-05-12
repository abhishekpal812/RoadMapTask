package com.example.roadmap.day3.service;

import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.repository.StudentRepository;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.exception.customClass.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(Integer studentId){
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not available for given id : " + studentId));
    }
    
    public Integer createStudent(Student student){
        return studentRepository.save(student).getId();
    }

    public Integer updateStudent(Student student){
        return studentRepository.save(student).getId();
    }

    public void deleteStudent(Integer studentId){
        studentRepository.deleteById(studentId);
    }

    public ResponseWrapper convertStudentResponse(Student student){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStudentId(student.getId());
        responseWrapper.setStudentName(student.getName());
        responseWrapper.setStudentCity(student.getCity());
        return responseWrapper;
    }
}
