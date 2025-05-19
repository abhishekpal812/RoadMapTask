package com.example.roadmap.unitTests;

import com.example.roadmap.day3.Entity.Address;
import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.response.ResponseWrapper;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class TestUtils {

    public static Student getStudent(){
        Student student = new Student();
        student.setId(1);
        student.setName("Pawan");
        student.setEmail("pawan@gmail.com");
        student.setContactNumber("9191818171");

        Address address = new Address();
        address.setId(1);
        address.setHouse("111, Shri balaji residency");
        address.setCity("Indore");
        address.setState("Madhya Pradesh");
        address.setPin(452010);

        student.setAddress(address);
        return student;
    }

    public static ResponseWrapper getResponseWrapper(){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStudentId(1);
        responseWrapper.setStudentName("Pawan");
        responseWrapper.setStudentEmail("pawan@gmail.com");
        responseWrapper.setCity("Indore");
        responseWrapper.setState("Madhya Pradesh");
        responseWrapper.setPin(452010);
        responseWrapper.setHouse("111, Shri balaji residency");
        return responseWrapper;
    }

}
