package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Mapper;
import com.dmitrenko.database.model.domain.Person;
import com.dmitrenko.database.model.dto.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonViewMapper implements Mapper<PersonView, Person> {

    @Override
    public PersonView from(Person source) {
        return new PersonView()
                .setId(source.getId())
                .setFirstName(source.getFirstName())
                .setLastName(source.getLastName())
                .setPhoneNumber(source.getPhoneNumber())
                .setEmail(source.getEmail())
                .setCounty(source.getCounty())
                .setUserId(source.getUser().getId())
                .setCreatedAt(source.getCreatedAt())
                .setUpdatedAt(source.getUpdatedAt());
    }
}
