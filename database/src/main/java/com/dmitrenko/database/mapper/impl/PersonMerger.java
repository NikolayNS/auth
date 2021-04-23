package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Merger;
import com.dmitrenko.database.model.domain.Person;
import com.dmitrenko.database.model.dto.PersonUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonMerger implements Merger<Person, PersonUpdateRequest> {

    @Override
    public Person merge(Person target, PersonUpdateRequest source) {
        if (source.getFirstName() != null && !source.getFirstName().isBlank()) target.setFirstName(source.getFirstName());
        if (source.getLastName() != null && !source.getLastName().isBlank()) target.setLastName(source.getLastName());
        if (source.getPhoneNumber() != null && !source.getPhoneNumber().isBlank()) target.setPhoneNumber(source.getPhoneNumber());
        if (source.getEmail() != null && !source.getEmail().isBlank()) target.setEmail(source.getEmail());
        if (source.getCounty() != null && !source.getCounty().isBlank()) target.setCounty(source.getCounty());

        return target;
    }
}
