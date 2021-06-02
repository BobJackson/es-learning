package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person createOrUpdatePerson(Person person);

    Iterable<Person> batchCreatePerson(List<Person> people);

    Optional<Person> queryById(Long id);

    void deletePerson(Long id);

    void batchDeletePeople(List<Long> ids);

    Page<Person> query(PersonQueryDTO query);
}
