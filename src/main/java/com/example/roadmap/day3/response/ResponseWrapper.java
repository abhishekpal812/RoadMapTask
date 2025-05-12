package com.example.roadmap.day3.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {
    private Integer studentId;
    private String studentName;
    private String studentCity;
    private Integer statusCode;
    private String errorMessage;

    public ResponseWrapper(Integer statusCode,String errorMessage){
        this.statusCode=statusCode;
        this.errorMessage=errorMessage;
    }
}
