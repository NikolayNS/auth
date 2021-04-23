package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Mapper;
import com.dmitrenko.database.model.domain.Role;
import com.dmitrenko.database.model.dto.RoleView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleViewMapper implements Mapper<RoleView, Role> {

    @Override
    public RoleView from(Role source) {
        return new RoleView()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setRoleType(source.getRoleType().getValue())
                .setCreatedAt(source.getCreatedAt())
                .setUpdatedAt(source.getUpdatedAt());
    }
}
