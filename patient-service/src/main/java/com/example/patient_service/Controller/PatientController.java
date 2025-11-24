package com.example.patient_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class PatientController {
    @GetMapping("/get")
    public String get(){
        return "hi hello";
    }
}
