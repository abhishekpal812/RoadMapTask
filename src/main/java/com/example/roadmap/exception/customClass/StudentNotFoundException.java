package com.example.roadmap.exception.customClass;

public class StudentNotFoundException extends RuntimeException{

    StudentNotFoundException(){}

    public StudentNotFoundException(String message){
        super(message);
    }
}
