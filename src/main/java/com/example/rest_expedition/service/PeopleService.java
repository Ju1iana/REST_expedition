package com.example.rest_expedition.service;

import com.example.rest_expedition.model.Calculator;
import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.repository.PeopleRepository;
import com.example.rest_expedition.util.PersonErrorResponse;
import com.example.rest_expedition.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
  private final PeopleRepository peopleRepository;
  private final Calculator calculator;

  @Autowired
  public PeopleService(PeopleRepository repository, Calculator calculator) {
    this.peopleRepository = repository;
    this.calculator = calculator;
  }

  public double calculateCalories(Person person) {
    return calculator.calc(person, person.getPersonGender());
  }

  @Transactional
  public void addPerson(Person person) {
    peopleRepository.save(person);
  }

  public List<Person> getAllPeople() {
    return peopleRepository.findAll();
  }

  public Person getPersonById(int id) {
    Optional<Person> foundPerson = Optional.ofNullable(peopleRepository.findById(id));
    return foundPerson.orElseThrow(PersonNotFoundException::new);

    /*return peopleRepository.findById(id);*/
  }

  public void deleteById(int id) {
    peopleRepository.deleteById(id);
  }
}
