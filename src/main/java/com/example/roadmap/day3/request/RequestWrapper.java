package com.example.roadmap.day3.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@Data
public class RequestWrapper {
    private Integer id;
    private String name;
    private String contactNumber;
    private String email;
    private String house;
    private String city;
    private String state;
    private Integer pin;
}
