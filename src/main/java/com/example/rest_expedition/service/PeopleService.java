package com.example.rest_expedition.service;

import com.example.rest_expedition.model.Calculator;
import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
  private final PeopleRepository repository;
  private final Calculator calculator;

  public PeopleService(PeopleRepository repository, Calculator calculator) {
    this.repository = repository;
    this.calculator = calculator;
  }

  public double calculateCalories(Person person){
    return calculator.calc(person, person.getPersonGender());
  }

  public void addPerson(Person person){
    repository.save(person);
  }

  public List<Person> getAllPeople(){
    return repository.findAll();
  }
}
