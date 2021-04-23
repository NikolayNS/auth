package com.dmitrenko.database.service;

import com.dmitrenko.database.model.dto.PersonAddRequest;
import com.dmitrenko.database.model.dto.PersonUpdateRequest;
import com.dmitrenko.database.model.dto.PersonView;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonView add(PersonAddRequest request);

    PersonView update(PersonUpdateRequest request);

    PersonView get(UUID id);

    List<PersonView> getAll();

    boolean delete(UUID id);
}
