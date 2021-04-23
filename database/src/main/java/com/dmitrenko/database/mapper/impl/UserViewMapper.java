package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Mapper;
import com.dmitrenko.database.model.domain.User;
import com.dmitrenko.database.model.dto.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserViewMapper implements Mapper<UserView, User> {

    private final PersonViewMapper personViewMapper;
    private final RoleViewMapper roleViewMapper;

    @Override
    public UserView from(User source) {
        return new UserView()
                .setId(source.getId())
                .setLogin(source.getLogin())
                .setPassword(source.getPassword())
                .setPersonView(personViewMapper.from(source.getPerson()))
                .setRoleViews(roleViewMapper.from(source.getRoles()))
                .setCreatedAt(source.getCreatedAt())
                .setUpdatedAt(source.getUpdatedAt());
    }
}
