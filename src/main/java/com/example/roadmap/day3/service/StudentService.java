package com.example.roadmap.day3.service;

import com.example.roadmap.day3.Entity.Address;
import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.repository.StudentRepository;
import com.example.roadmap.day3.request.RequestWrapper;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.exception.customClass.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(Integer studentId){
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not available for given id : " + studentId));
    }
    
    public Integer createStudent(RequestWrapper requestWrapper){
        return studentRepository.save(prepareStudentObject(requestWrapper)).getId();
    }

    private Student prepareStudentObject(RequestWrapper requestWrapper) {

        Address address = new Address();
        address.setHouse(requestWrapper.getHouse());
        address.setCity(requestWrapper.getCity());
        address.setState(requestWrapper.getState());
        address.setPin(requestWrapper.getPin());

        Student student = new Student();
        student.setName(requestWrapper.getName());
        student.setContactNumber(requestWrapper.getContactNumber());
        student.setEmail(requestWrapper.getEmail());
        student.setAddress(address);

        return student;
    }


    public Integer updateStudent(RequestWrapper requestWrapper){
        Student student = studentRepository.findById(requestWrapper.getId()).orElseThrow(() -> new StudentNotFoundException("Student not available for given id : " + requestWrapper.getId()));
        student.setName(requestWrapper.getName());
        student.setContactNumber(requestWrapper.getContactNumber());
        student.setEmail(requestWrapper.getEmail());

        Address address = student.getAddress();
        address.setCity(requestWrapper.getCity());
        address.setPin(requestWrapper.getPin());
        address.setState(requestWrapper.getState());
        address.setHouse(requestWrapper.getHouse());

        student.setAddress(address);
        return studentRepository.save(student).getId();
    }

    public void deleteStudent(Integer studentId){
        studentRepository.deleteById(studentId);
    }

    public ResponseWrapper convertStudentResponse(Student student){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStudentId(student.getId());
        responseWrapper.setStudentName(student.getName());
        responseWrapper.setStudentContactNumber(student.getContactNumber());
        responseWrapper.setStudentEmail(student.getEmail());
        return responseWrapper;
    }
}
