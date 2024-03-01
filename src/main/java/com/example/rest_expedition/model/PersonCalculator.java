package com.example.rest_expedition.model;

import com.example.rest_expedition.repository.PeopleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

// ебёмся с обычными и походными калориями
@Component
@Data
@NoArgsConstructor
public class PersonCalculator {
  private static double amount;
  private static double allCalories; // total answer
  private double betta; // коэффициент вида похода, для пешего 1, для лыжного 1.2, для горного 1.3
  private double gamma; // коэффициент сложности (категории) похода

  private int duration;
  private static int numberOfPeople;

  // расчёт обычных калорий на добавленного человека (для таблицы)
  public double calc(Person person, PersonGender pGender) {
    final double A = 1.55; // коэффициент умеренного уровня активности
    double answer = 0;

    switch (pGender) {
      case MAN -> {
        answer = ((10 * person.getWeight() + 6.25 * person.getH() - 5 * person.getAge() + 5) * A);
      }
      case WOMAN -> {
        answer = ((10 * person.getWeight() + 6.25 * person.getH() - 5 * person.getAge() - 161) * A);
      }
    }
    System.out.println("1: " + answer);
    return answer;
  }

  // расчёт обычных калорий на весь поход
  public void calcUsualCalories(PeopleRepository repository) {
    amount = 0;
    List<Person> people = repository.findAll();
    for (Person person : people) {
      amount += person.getCalories();
    }
    setAmount(amount * duration);
    setNumberOfPeople(people.size());

    /*// просто считает калории для каждого человека и выводит в консоль
    for (int i = 0; i < people.size(); i++) {
      System.out.println((i+1) + ": " + people.get(i).getCalories() * betta * gamma * 1.2 * duration);
    }*/
  }

  // расчёт походных калорий
  public void calcHikingCalories() {
    final double a = 1.2; // коэффициент преобразования повседневной траты калорий;
    double answer = amount * betta * a * gamma;
    setAllCalories(answer);
    System.out.println("calcHikingCalories() " + "betta: " + betta + "gamma: " + gamma);
    System.out.println("answer: " + answer + "amount: " + amount);
  }

  public double getAmount() {
    return amount;
  }

  private void setAmount(double v) {
    amount = v;
  }

  public double getAllCalories() {
    return allCalories;
  }

  public void setAllCalories(double allCalories) {
    PersonCalculator.allCalories = allCalories;
  }

  public int getNumberOfPeople() {
    return numberOfPeople;
  }

  public void setNumberOfPeople(int numberOfPeople) {
    PersonCalculator.numberOfPeople = numberOfPeople;
  }
}

