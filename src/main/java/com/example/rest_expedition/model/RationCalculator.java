package com.example.rest_expedition.model;

import com.example.rest_expedition.repository.PeopleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RationCalculator {

  private final PeopleRepository repository;
  private final PersonCalculator personCalculator;

  public RationCalculator(PeopleRepository repository, PersonCalculator personCalculator) {
    this.repository = repository;
    this.personCalculator = personCalculator;
  }

  public List<Integer> initCalories() {
    List<Person> people = repository.findAll();
    List<Double> calories = new ArrayList<>();

    // забираем калории у людей
    for (Person person : people) {
      calories.add(person.getCalories());
    }

    // считаем проценты
    List<Double> percentList = new ArrayList<>();
    for (int i = 0; i < calories.size(); i++) {
      percentList.add((100 * calories.get(i))/calories.stream().mapToDouble(a -> a).sum());
    }

    // считаем калории по процентам
    System.out.println("all calories: " + personCalculator.getAllCalories());
    List<Integer> listCalories = new ArrayList<>();
    for (int i = 0; i < percentList.size(); i++) {
      listCalories.add((int)(percentList.get(i) * personCalculator.getAllCalories()) / 100);
    }
    return listCalories;
  }
}

