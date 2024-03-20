package com.example.rest_expedition.controller;

import com.example.rest_expedition.dto.PersonDTO;
import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.service.PersonCalculator;
import com.example.rest_expedition.service.PeopleService;
import com.example.rest_expedition.util.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class PeopleController {

  private final PeopleService peopleService;
  private final ModelMapper modelMapper;

  public PeopleController(PeopleService peopleService, PersonCalculator personCalculator, ModelMapper modelMapper) {
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
  public List<PersonDTO> getAllPeople() {
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

  @Validated // Возможно надо будет удалить. Было добавлено до c46e7767 tuchka <u.pankratova@internet.ru> on 03.03.2024 at 20:11
  @Operation(summary = "Post duration then type (hiking, skiing, mountain), then difficulty")
  @ResponseStatus(code = HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST, value = "/addParameters")
  public ResponseEntity<HttpStatus> addDuration(@RequestParam @Min(1) int duration,
                                            @RequestParam double type,
                                            @RequestParam double difficulty) {

      // TODO: если понадобится, то сделать обработку ошибки
      peopleService.setDuration(duration);
      peopleService.setType(type);
      peopleService.setDifficulty(difficulty);

      peopleService.setTotalCal();
      return ResponseEntity.ok(HttpStatus.OK);
  }

  @Operation(summary = "Partial update by id")
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  @PatchMapping("/update")
  public void updatePerson(@RequestParam("id") int id,
                         @RequestBody (required = false) PersonDTO personDTO){
    peopleService.updateById(id, personDTO);
  }

  @Operation(summary = "Get total calories")
  @ResponseStatus(code = HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET, value = "/getTotalCal")
  public double getTotalCal() {
    return peopleService.getTotalCal();
  }

  public Person convertToPerson(PersonDTO personDTO) {
    return modelMapper.map(personDTO, Person.class);
  }

  public PersonDTO convertToPersonDTO(Person person) {
    return modelMapper.map(person, PersonDTO.class);
  }
}
