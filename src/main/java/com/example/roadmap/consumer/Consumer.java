package com.example.roadmap.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = {"NewTopic"},groupId = "abc")
    public void consumeMessage(String message){
        System.out.println(message);
    }
}
