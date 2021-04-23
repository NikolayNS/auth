package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Mapper;
import com.dmitrenko.database.model.domain.Person;
import com.dmitrenko.database.model.dto.PersonAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonMapper implements Mapper<Person, PersonAddRequest> {

    @Override
    public Person from(PersonAddRequest source) {
        return new Person()
                .setFirstName(source.getFirstName())
                .setLastName(source.getLastName())
                .setPhoneNumber(source.getPhoneNumber())
                .setEmail(source.getEmail())
                .setCounty(source.getCounty());
    }
}
