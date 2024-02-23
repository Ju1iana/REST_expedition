package com.example.rest_expedition.service;

import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
  private final PeopleRepository repository;

  public PeopleService(PeopleRepository repository) {
    this.repository = repository;
  }

  public void addPerson(Person person){
    repository.save(person);
  }

  public List<Person> getAllPeople(){
    return repository.findAll();
  }
}
