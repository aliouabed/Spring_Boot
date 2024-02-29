package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.person.service.PersonEntityManager;
import com.example.demo.person.service.Person;
import com.example.demo.person.service.PersonEntityManagerFromFactory;
import com.example.demo.person.service.PersonJdbcTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@RestController
@RequestMapping("/rest/person")
public class PersonRestController {

	@Autowired
	private PersonJdbcTemplate persondbcService;

	@Autowired
	private PersonEntityManagerFromFactory personEntityFromFactoryService;

	@Autowired
	private PersonEntityManager personEntityService;

	@RequestMapping(value = "/create/jdbc", method = RequestMethod.GET)
	public String createPersonWithJDBC() {
		persondbcService.addAPerson("hatim", "lechgar", 33);
		return "Hatim Created with jdbc template";
	}

	@RequestMapping(value = "/create/em/verbose", method = RequestMethod.GET)
	public String createPersonWithEntityManagerFactory() throws Exception {
		personEntityFromFactoryService.addAPersonVerbose("hatim", "lechgar", 33);
		return "Hatim Created with EntityManager verbose";
	}

	@RequestMapping(value = "/create/em", method = RequestMethod.POST, consumes = "application/json" )
	public String createPersonWithEntityManager(@RequestBody Person person) {
		personEntityService.addAPerson(person);
		return "Hatim Created with EntityManager";
	}



	@GetMapping("/info/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        Person person = personEntityService.findPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


	@GetMapping("/info/batch")
    public ResponseEntity<List<Person>> getAllPersons(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Person> persons = personEntityService.findAllPersons(page, size);
        return ResponseEntity.ok(persons);
    }

}