package com.example.demo.rest;


import com.example.demo.models.MockPerson;
import com.example.demo.service.PersonValidatorService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/rest")
public class HelloWorldRestController {


    @Autowired
	private  PersonValidatorService validatorService;

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		return "welcome";
	}
  
	@RequestMapping(value = "/HiJason", method = RequestMethod.GET)
	//@GetMapping(value = "/HiJason")
	public MockPerson sayHelloAgain() {
		return new MockPerson("Generic","Person");
	}



	//@RequestMapping(value = "/HiJason", method = RequestMethod.POST ,  consumes = "application/json")
	@PostMapping(value = "/HiJason", consumes = "application/json")
	public ResponseEntity<?> checkRulesAndsayHello(@RequestBody MockPerson person) {
		if (!validatorService.isValid(person)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First name and last name must be between 4 and 10 characters");
        }
        return ResponseEntity.ok(person);
	}	
}