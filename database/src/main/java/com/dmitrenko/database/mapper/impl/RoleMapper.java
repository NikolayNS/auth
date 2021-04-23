package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Mapper;
import com.dmitrenko.database.model.domain.Role;
import com.dmitrenko.database.model.domain.RoleType;
import com.dmitrenko.database.model.dto.RoleAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMapper implements Mapper<Role, RoleAddRequest> {

    @Override
    public Role from(RoleAddRequest source) {
        return new Role()
                .setDescription(source.getDescription())
                .setRoleType(RoleType.fromValue(source.getRoleType()));
    }
}
