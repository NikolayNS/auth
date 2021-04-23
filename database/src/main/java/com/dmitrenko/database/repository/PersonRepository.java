package com.dmitrenko.database.repository;

import com.dmitrenko.database.model.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByFirstNameAndPhoneNumber(String firstName, String phoneNumber);
}
