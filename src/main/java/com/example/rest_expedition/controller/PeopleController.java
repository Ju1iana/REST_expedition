package com.example.rest_expedition.controller;

import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8080")
public class PeopleController {

  private final PeopleService peopleService;

  public PeopleController(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @Operation(summary = "Add a person")
  @ResponseStatus(code = HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/addPerson")
  public void addPerson(@RequestBody Person person){
    peopleService.calculateCalories(person);
    peopleService.addPerson(person);
    System.out.println(person);
  }

  @Operation(summary = "Get all people")
  @ResponseStatus(code = HttpStatus.FOUND)
  @RequestMapping(method = RequestMethod.GET, value = "/getAllPeople")
  public List<Person> getAllPerson(){
    return peopleService.getAllPeople();
  }


  @Operation(summary = "Get person by id")
  @ResponseStatus(code = HttpStatus.FOUND)
  @RequestMapping(method = RequestMethod.GET, value = "/getPersonById{id}")
  public Person getPersonById(@RequestParam("id") int id){
    return peopleService.getPersonById(id);
  }

}
