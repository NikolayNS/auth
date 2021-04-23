package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Mapper;
import com.dmitrenko.database.model.domain.Person;
import com.dmitrenko.database.model.domain.Role;
import com.dmitrenko.database.model.domain.User;
import com.dmitrenko.database.model.dto.UserAddRequest;
import com.dmitrenko.database.util.Triple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, Triple<UserAddRequest, Person, List<Role>>> {

    @Override
    public User from(Triple<UserAddRequest, Person, List<Role>> source) {
        return new User()
                .setLogin(source.getLeft().getLogin())
                .setPassword(source.getLeft().getPassword())
                .setPerson(source.getCentre())
                .setRoles(source.getRight());
    }
}
