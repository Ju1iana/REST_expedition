package com.example.rest_expedition.controller;

import com.example.rest_expedition.dto.PersonDTO;
import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.service.PeopleService;
import com.example.rest_expedition.util.PersonErrorResponse;
import com.example.rest_expedition.util.PersonNotCreatedException;
import com.example.rest_expedition.util.PersonNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:8080")
public class PeopleController {

  private final PeopleService peopleService;
  private final ModelMapper modelMapper;

  public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
    this.peopleService = peopleService;
    this.modelMapper = modelMapper;
  }

  @Operation(summary = "Get person by id")
  @ResponseStatus(code = HttpStatus.FOUND)
  @RequestMapping(method = RequestMethod.GET, value = "/getPersonById{id}")
  public PersonDTO getPersonById(@RequestParam("id") int id) {
    return convertToPersonDTO(peopleService.getPersonById(id));
  }

  @Operation(summary = "Get all people")
  @ResponseStatus(code = HttpStatus.FOUND)
  @RequestMapping(method = RequestMethod.GET, value = "/getAllPeople")
  public List<PersonDTO> getAllPerson() {
    return peopleService.getAllPeople().stream()
      .map(this::convertToPersonDTO)
      .collect(Collectors.toList());
  }

  @Operation(summary = "Add a person")
  @ResponseStatus(code = HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/addPerson")
  public ResponseEntity<HttpStatus> addPerson(@RequestBody @Valid PersonDTO personDTO,
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
    peopleService.addPerson(convertToPerson(personDTO));
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @Operation(summary = "Delete person by id")
  @ResponseStatus(code = HttpStatus.OK)
  @RequestMapping(method = RequestMethod.DELETE, value = "/deletePersonById")
  public ResponseEntity<HttpStatus> deleteById(@RequestParam("id") int id) {
    peopleService.deleteById(id);
    return ResponseEntity.ok(HttpStatus.OK);
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

  public Person convertToPerson(PersonDTO personDTO) {
    return modelMapper.map(personDTO, Person.class);
  }

  public PersonDTO convertToPersonDTO(Person person) {
    return modelMapper.map(person, PersonDTO.class);
  }
}
