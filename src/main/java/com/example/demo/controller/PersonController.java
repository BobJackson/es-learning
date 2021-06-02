package com.example.demo.controller;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonQueryDTO;
import com.example.demo.service.PersonService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/persons")
    public Person save(Person person) {
        return personService.createOrUpdatePerson(person);
    }

    @PutMapping("/persons")
    public Person update(Person person) {
        return personService.createOrUpdatePerson(person);
    }


    @GetMapping("/persons/{id}")
    public Person queryById(@PathVariable Long id) {
        return personService.queryById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Not find Person of id is {%d}", id)));
    }

    @GetMapping("/persons/")
    public Page<Person> list(PersonQueryDTO query) {
        return personService.query(query);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/persons/{id}")
    public void deleteById(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @PostMapping("/persons/actions/batch-save")
    public Iterable<Person> batchSave(List<Person> people) {
        return personService.batchCreatePerson(people);
    }

    @DeleteMapping("/persons/actions/batch-delete")
    public void batchDelete(List<Long> ids) {
        personService.batchDeletePeople(ids);
    }


}
