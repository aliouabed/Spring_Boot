package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.models.MockPerson; 

@Service
public class PersonValidatorService {

    public boolean isValid(MockPerson person) {
        return isValidLength(person.getFirstName()) && isValidLength(person.getLastName());
    }

    private boolean isValidLength(String value) {
        return value != null && value.length() >= 4 && value.length() <= 10;
    }
}
