package com.example.roadmap.day3.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
 @NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {
    private Integer studentId;
    private String studentName;
    private String studentContactNumber;
    private String studentEmail;
    private String house;
    private String city;
    private String state;
    private Integer pin;
    private Integer statusCode;
    private String errorMessage;

    public ResponseWrapper(Integer statusCode,String errorMessage){
        this.statusCode=statusCode;
        this.errorMessage=errorMessage;
    }

    public  ResponseWrapper(Integer statusCode,Integer studentId){
        this.statusCode=statusCode;
        this.studentId=studentId;
    }
}
