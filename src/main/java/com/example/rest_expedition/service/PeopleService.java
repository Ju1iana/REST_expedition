package com.example.rest_expedition.service;

import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.repository.PeopleRepository;
import com.example.rest_expedition.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
  private final PeopleRepository peopleRepository;
  private final PersonCalculator personCalculator;

  @Autowired
  public PeopleService(PeopleRepository repository, PersonCalculator calculator) {
    this.peopleRepository = repository;
    this.personCalculator = calculator;
  }

  @Transactional
  public void addPerson(Person person) {
    enrichPerson(person);
    peopleRepository.save(person);
  }

  public List<Person> getAllPeople() {
    return peopleRepository.findAll();
  }

  public Person getPersonById(int id) {
    Optional<Person> foundPerson = Optional.ofNullable(peopleRepository.findById(id));
    return foundPerson.orElseThrow(PersonNotFoundException::new);
  }

  @Transactional
  public void deleteById(int id) {
    Optional<Person> foundPerson = Optional.ofNullable(peopleRepository.findById(id));
    if (foundPerson.isPresent())
      peopleRepository.deleteById(id);
    else throw new PersonNotFoundException();
  }

  public void setDuration(int duration) {
    personCalculator.setDuration(duration);
  }

  public void setType(double type){
    personCalculator.setBetta(type);
  }

  public void setDifficulty(double difficulty){
    personCalculator.setGamma(difficulty);
  }

  public void setTotalCal(){
    personCalculator.calcUsualCalories(peopleRepository); // считает обычные калории на все дни похода
    personCalculator.calcHikingCalories(); // считает итоговые калории на весь поход
  }

  public double getTotalCal(){
    return personCalculator.getAllCalories();
  }

  private void enrichPerson(Person person) {
    person.setCalories(calculateCalories(person));
  }

  private double calculateCalories(Person person) {
    return personCalculator.calc(person, person.getPersonGender());
  }
}
