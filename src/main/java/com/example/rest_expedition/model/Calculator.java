package com.example.rest_expedition.model;

import com.example.rest_expedition.repository.PeopleRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class Calculator {
  private static double amount;
  private static double allCalories; // total answer
  private double betta; // коэффициент вида похода, для пешего 1, для лыжного 1.2, для горного 1.3
  private double gamma; // коэффициент сложности (категории) похода, 1 для первой категории, 1.1 для второй, 1.2 для третьей и т.д

  private int duration;
  private static int numberOfPeople;
  private int rationWeight;
  private int rationCalories;
  private int rightRation;

  public void calcDuration(PeopleRepository repository) {
    amount = 0;
    List<Person> people = repository.findAll();
    for (Person person : people) {
      amount += person.getCalories();
    }
    setAmount(amount * duration);
    setNumberOfPeople(people.size());
  }

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

  public double calcAll() {
    final double a = 1.2; // коэффициент преобразования повседневной траты калорий;
    double answer = amount * betta * a * gamma;
    setAllCalories(answer);
    System.out.println("calcAll()" + "betta: " + betta + "gamma: " + gamma);
    System.out.println("answer: " + answer + "amount: " + amount);
    return answer;
  }

  /*  public Ration numberOfRation(RationRepository repository) {
        int closestNumber = Integer.MAX_VALUE;
        List<Ration> rationList = repository.findAll();
        for(Ration ration : rationList) {
            int result = ration.getCalories() * duration * numberOfPeople;
            if (Math.abs(result - allCalories) < Math.abs(closestNumber - allCalories)) {
                closestNumber = result;
                rightRation = ration.getId();
                System.out.println(allCalories + " - " + "closet number: " + closestNumber +
                        ", calories: " + ration.getCalories() + ", ID: " + ration.getId());
            }
        }
        setRightRation(rightRation);
        System.out.println("---- YOUR RATION: #" + getRightRation() + " ---- Number of people: " + numberOfPeople);
        return repository.findById(rightRation);
    }*/

  public static double getAmount() {
    return amount;
  }

  private void setAmount(double v) {
    amount = v;
  }

  public static double getAllCalories() {
    return allCalories;
  }

  public static void setAllCalories(double allCalories) {
    Calculator.allCalories = allCalories;
  }

  public static int getNumberOfPeople() {
    return numberOfPeople;
  }

  public static void setNumberOfPeople(int numberOfPeople) {
    Calculator.numberOfPeople = numberOfPeople;
  }
}

