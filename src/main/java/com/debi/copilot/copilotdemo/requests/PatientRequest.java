package com.debi.copilot.copilotdemo.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {
    
    private String firstName;
    private String lastName;
    private int age;
}
