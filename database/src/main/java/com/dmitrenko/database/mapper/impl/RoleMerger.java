package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Merger;
import com.dmitrenko.database.model.domain.Role;
import com.dmitrenko.database.model.domain.RoleType;
import com.dmitrenko.database.model.dto.RoleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMerger implements Merger<Role, RoleUpdateRequest> {

    @Override
    public Role merge(Role target, RoleUpdateRequest source) {
        if (source.getDescription() != null && !source.getDescription().isBlank()) target.setDescription(source.getDescription());
        if (source.getRoleType() != null && !source.getRoleType().isBlank()) target.setRoleType(RoleType.fromValue(source.getRoleType()));

        return target;
    }
}
