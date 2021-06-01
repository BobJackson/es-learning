package com.example.demo;

import com.example.demo.domain.Person;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.MultiGetItem;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final ElasticsearchOperations elasticsearchOperations;

    public PersonController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostMapping("/persons")
    public String save(@RequestBody Person person) {
        return elasticsearchOperations.save(person).getId().toString();
    }

    @GetMapping("/persons/{id}")
    public Person findById(@PathVariable("id") String id) {
        return elasticsearchOperations.get(id, Person.class);
    }

    @DeleteMapping("/persons/{id}")
    public void deleteById(@PathVariable String id) {
        elasticsearchOperations.delete(id, Person.class);
    }


    @GetMapping("/persons")
    public List<Person> list() {
        return elasticsearchOperations.multiGet(Query.findAll(), Person.class)
                .stream()
                .map(MultiGetItem::getItem)
                .collect(Collectors.toList());
    }


}