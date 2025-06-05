package com.example.roadmap.s2s;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "feignDemo", url = "http://localhost:8090/notification")
public interface NotificationFeignUtil {

    @PostMapping("/capture/{studentId}")
    public void captureStudentId(@PathVariable Integer studentId);

}
