package com.dmitrenko.database.service.impl;

import com.dmitrenko.database.mapper.impl.PersonMapper;
import com.dmitrenko.database.mapper.impl.PersonMerger;
import com.dmitrenko.database.mapper.impl.PersonViewMapper;
import com.dmitrenko.database.model.domain.Person;
import com.dmitrenko.database.model.dto.PersonAddRequest;
import com.dmitrenko.database.model.dto.PersonUpdateRequest;
import com.dmitrenko.database.model.dto.PersonView;
import com.dmitrenko.database.repository.PersonRepository;
import com.dmitrenko.database.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonViewMapper personViewMapper;
    private final PersonMapper personMapper;
    private final PersonMerger personMerger;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonView add(PersonAddRequest request) {
        if (personRepository.findByFirstNameAndPhoneNumber(request.getFirstName(), request.getPhoneNumber()).isPresent())
            throw new EntityExistsException(String.format("Person %s with phone %s already exist", request.getFirstName(), request.getPhoneNumber()));

        var person = personMapper.from(request);
        person = personRepository.saveAndFlush(person);

        return personViewMapper.from(person);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonView update(PersonUpdateRequest request) {
        var person = getOrTrow(request.getId());
        person = personMerger.merge(person, request);

        return personViewMapper.from(person);
    }

    private Person getOrTrow(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Person %s not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public PersonView get(UUID id) {
        return personViewMapper.from(getOrTrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonView> getAll() {
        return personViewMapper.from(personRepository.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(UUID id) {
        var person = getOrTrow(id);
        personRepository.delete(person);
        personRepository.flush();

        return personRepository.existsById(id);
    }
}
