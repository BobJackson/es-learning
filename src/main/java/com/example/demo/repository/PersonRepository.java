package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, Long> {
}
