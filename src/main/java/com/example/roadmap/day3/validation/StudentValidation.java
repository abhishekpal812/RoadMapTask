package com.example.roadmap.day3.validation;

import com.example.roadmap.day3.Entity.Student;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentValidation {

    public void notEmpty(Integer studentId){
        if(Objects.isNull(studentId) || studentId == 0){
            throw new IllegalArgumentException("Getting invalid student id : "+ studentId);
        }
    }

    public void validateStudentObject(Student student){
        if(Objects.isNull(student) || Objects.isNull(student.getName()) || student.getName().isEmpty() || Objects.isNull(student.getCity()) || student.getCity().isEmpty()){
            throw new IllegalArgumentException("Parameter missing in request json : "+ student);
        }
    }
}
