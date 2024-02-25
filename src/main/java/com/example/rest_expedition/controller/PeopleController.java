package com.example.rest_expedition.controller;

import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.service.PeopleService;
import com.example.rest_expedition.util.PersonErrorResponse;
import com.example.rest_expedition.util.PersonNotCreatedException;
import com.example.rest_expedition.util.PersonNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

  @Operation(summary = "Get person by id")
  @ResponseStatus(code = HttpStatus.FOUND)
  @RequestMapping(method = RequestMethod.GET, value = "/getPersonById{id}")
  public Person getPersonById(@RequestParam("id") int id) {
    return peopleService.getPersonById(id);
  }

  @Operation(summary = "Get all people")
  @ResponseStatus(code = HttpStatus.FOUND)
  @RequestMapping(method = RequestMethod.GET, value = "/getAllPeople")
  public List<Person> getAllPerson() {
    return peopleService.getAllPeople();
  }

  @Operation(summary = "Add a person")
  @ResponseStatus(code = HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/addPerson")
  public ResponseEntity<HttpStatus> addPerson(@RequestBody @Valid Person person,
                                              BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      StringBuilder errorMsg = new StringBuilder();
      List<FieldError> errors = bindingResult.getFieldErrors();
      for (FieldError error : errors) {
        errorMsg.append(error.getField())
          .append(" - ").append(error.getDefaultMessage())
          .append(";");
      }
      throw new PersonNotCreatedException(errorMsg.toString());
    }
    person.setCalories(peopleService.calculateCalories(person));
    peopleService.addPerson(person);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @Operation(summary = "Delete person by id")
  @ResponseStatus(code = HttpStatus.OK)
  @RequestMapping(method = RequestMethod.DELETE, value = "/deletePersonById")
  public void deleteById(@RequestParam("id") int id) {
    peopleService.deleteById(id);
  }


  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handlerException(PersonNotFoundException e) {
    PersonErrorResponse response = new PersonErrorResponse(
      "Person with this ID wasn't found!",
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404
  }

  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handlerException(PersonNotCreatedException e) {
    PersonErrorResponse response = new PersonErrorResponse(
      e.getMessage(),
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // BAD_REQUEST - 400
  }
}
