package com.example.roadmap.exception.customClass;

import java.util.Collection;

public class StudentNotFoundException extends RuntimeException{

    StudentNotFoundException(){}

    public StudentNotFoundException(String message){
        super(message);
    }
}
