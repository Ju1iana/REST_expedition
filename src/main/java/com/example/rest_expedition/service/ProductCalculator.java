package com.example.rest_expedition.service;

import com.example.rest_expedition.dto.ProductDTO;
import com.example.rest_expedition.model.Person;
import com.example.rest_expedition.model.Product;
import com.example.rest_expedition.model.Ration;
import com.example.rest_expedition.repository.PeopleRepository;
import com.example.rest_expedition.repository.ProductRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// работаем только с походными калориями
@Component
@Data
public class ProductCalculator {

  private final PeopleRepository peopleRepository;
  private final ProductRepository productRepository;
  private final PersonCalculator personCalculator;
  private static ModelMapper mapper;

  private double breakfastCalories;
  private double lunchCalories;
  private double dinnerCalories;
  private double snackCalories;

  private List<Integer> listCalories;
  private static List<Ration> totalListRation;
  private static List<List<Ration>> allRations = new ArrayList<>();

  @Autowired
  public ProductCalculator(PeopleRepository repository, ProductRepository productRepository, PersonCalculator calculator, ModelMapper mapper) {
    this.peopleRepository = repository;
    this.productRepository = productRepository;
    this.personCalculator = calculator;
    this.mapper = mapper;
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
      percentList.add((100 * calories.get(i)) / calories.stream().mapToDouble(a -> a).sum());
    }

    // считаем калории по процентам
    System.out.println("From RationController - all calories - " + personCalculator.getAllCalories());
    List<Integer> totalCaloriesList = new ArrayList<>();
    for (int i = 0; i < percentList.size(); i++) {
      totalCaloriesList.add((int) (percentList.get(i) * personCalculator.getAllCalories()) / 100);
    }
    listCalories = totalCaloriesList;
    return totalCaloriesList;
  }

  // считаем калорийность суточного рациона исходя из процентного соотношения

  public List<Ration> initEatingCalories() {
    breakfastCalories = (personCalculator.getAllCalories() / personCalculator.getDuration()) * 0.35;
    lunchCalories = (personCalculator.getAllCalories() / personCalculator.getDuration()) * 0.3;
    dinnerCalories = (personCalculator.getAllCalories() / personCalculator.getDuration()) * 0.25;
    snackCalories = (personCalculator.getAllCalories() / personCalculator.getDuration()) * 0.1;

    totalListRation = new ArrayList<>(personCalculator.getDuration());

    initListProductsForBreakfastLunchDinnerAndSnack();

    return totalListRation;
  }

  public void initListProductsForBreakfastLunchDinnerAndSnack(){
    generateRations(productRepository.getDinner(), getDinnerCalories(), personCalculator.getDuration(), "Ужин");
    generateRations(productRepository.getLunch(), getLunchCalories(), personCalculator.getDuration(), "Обед");
    generateRations(productRepository.getSnack(), getSnackCalories(), personCalculator.getDuration(), "Перекус");
    generateRations(productRepository.getBreakfast(), getBreakfastCalories(), personCalculator.getDuration(), "Завтрак");
  }

  public static List<Ration> generateRations(List<Product> productList, double calories, int numberOfRations, String nameOfMeal) {
    for (int i = 0; i < numberOfRations; i++) {
      List<Product> productsInRation = new ArrayList<>();
      double remainingCalories = calories;

      boolean hasProtein = false; // Флаг наличия продуктов с белками
      boolean hasCarbohydrates = false; // Флаг наличия продуктов с углеводами

      while (remainingCalories > 0) {
        Product randomProduct = getRandomProduct(productList);

        // Проверяем, содержит ли продукт белки или углеводы, и он подходит по калориям
        if ((randomProduct.getProteins() > 0 && !hasProtein) ||
          (randomProduct.getCarbohydrates() > 0 && !hasCarbohydrates)) {
          if (randomProduct.getCalories_per_100g() > remainingCalories) {
            break; // Прерываем цикл, если добавление продукта приведет к превышению калорийности
          }
          productsInRation.add(randomProduct);
          remainingCalories -= (randomProduct.getCalories_per_100g());

          if (i == 0) {
            // Помечаем, что мы уже добавили продукт с белками или углеводами
            if (randomProduct.getProteins() > 10) {
              hasProtein = true;
            } else if (randomProduct.getCarbohydrates() > 30) {
              hasCarbohydrates = true;
            }
          }
          if (hasProtein && hasCarbohydrates) {
            break;
          }
        }

          if (i == 1) { // Обед
            // Помечаем, что мы уже добавили продукт с белками или углеводами
            if (randomProduct.getProteins() > 12) {
              hasProtein = true;
            } else if (randomProduct.getCarbohydrates() > 35) {
              hasCarbohydrates = true;
            }
          }
          if (hasProtein && hasCarbohydrates) {
            break;
          }
        }

      // Дополняем рацион до необходимой калорийности другими продуктами
      while (remainingCalories > 0) {
        Product randomProduct = getRandomProduct(productList);
        if (randomProduct.getCalories_per_100g() > remainingCalories) {
          break; // Прерываем цикл, если добавление продукта приведет к превышению калорийности
        }
        productsInRation.add(randomProduct);
        remainingCalories -= (randomProduct.getCalories_per_100g());
      }
      Ration ration1 = new Ration(i+1, nameOfMeal, convertToProductDTOList(productsInRation));
      totalListRation.add(i, ration1);
    }
    return totalListRation;
  }

  public static Product getRandomProduct(List<Product> products){
    Random random = new Random();
    int randomIndex = random.nextInt(products.size());
    return products.get(randomIndex);
  }

  public static List<ProductDTO> convertToProductDTOList(List<Product> productList){
    return productList.stream()
      .map(ProductCalculator::convertToProductDTO)
      .collect(Collectors.toList());
  }

  public static ProductDTO convertToProductDTO(Product product){
    return mapper.map(product, ProductDTO.class);
  }
}

