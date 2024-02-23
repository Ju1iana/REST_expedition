package com.example.rest_expedition.repository;

import com.example.rest_expedition.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

  Person findById(int id);

}
