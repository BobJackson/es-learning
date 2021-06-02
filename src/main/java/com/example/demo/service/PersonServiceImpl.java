package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonQueryDTO;
import com.example.demo.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person createOrUpdatePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public Iterable<Person> batchCreatePerson(List<Person> people) {
        return repository.saveAll(people);
    }

    @Override
    public Optional<Person> queryById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deletePerson(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void batchDeletePeople(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public Page<Person> query(PersonQueryDTO query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        return repository.findAll(pageable);
    }


}
