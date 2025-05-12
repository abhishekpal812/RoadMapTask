package com.example.roadmap.exception.handler;

import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.exception.customClass.StudentNotFoundException;
import org.hibernate.StaleObjectStateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseWrapper handleStudentNotFoundException(StudentNotFoundException exception){
        return new ResponseWrapper(500,exception.getMessage());
    }

    @ExceptionHandler({StaleObjectStateException.class})
    public ResponseWrapper handleStaleObjectStateException(StaleObjectStateException exception){
        return new ResponseWrapper(500,exception.getMessage());
    }
}
