package com.example.roadmap.day3.validation;

import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.request.RequestWrapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentValidation {

    public void notEmpty(Integer studentId){
        if(Objects.isNull(studentId) || studentId == 0){
            throw new IllegalArgumentException("Getting invalid student id : "+ studentId);
        }
    }

    public void validateStudentObject(RequestWrapper requestWrapper){
        if(Objects.isNull(requestWrapper) || Objects.isNull(requestWrapper.getName()) || requestWrapper.getName().isEmpty() || Objects.isNull(requestWrapper.getContactNumber()) || requestWrapper.getContactNumber().isEmpty() || Objects.isNull(requestWrapper.getEmail()) || requestWrapper.getEmail().isEmpty()){
            throw new IllegalArgumentException("Parameter missing in request json : "+ requestWrapper);
        }
    }
}
