package com.example.rest_expedition.model;

import com.example.rest_expedition.repository.PeopleRepository;
import com.example.rest_expedition.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// работаем только с походными калориями
@Component
public class ProductCalculator {

  private final PeopleRepository peopleRepository;
  private final ProductRepository productRepository;
  private final PersonCalculator personCalculator;

  private List<Integer> listCalories;

  public ProductCalculator(PeopleRepository repository, ProductRepository productRepository, PersonCalculator personCalculator) {
    this.peopleRepository = repository;
    this.productRepository = productRepository;
    this.personCalculator = personCalculator;
  }

  // расчёт калорий по процентам для каждого человека в походе
  public List<Integer> initCalories() {
    List<Person> people = peopleRepository.findAll();
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
    System.out.println("From RationController - all calories - " + personCalculator.getAllCalories());
    List<Integer> totalCaloriesList = new ArrayList<>();
    for (int i = 0; i < percentList.size(); i++) {
      totalCaloriesList.add((int)(percentList.get(i) * personCalculator.getAllCalories()) / 100);
    }
    listCalories = totalCaloriesList;
    return totalCaloriesList;
  }

  public void getProductsByType(){
  }
}

