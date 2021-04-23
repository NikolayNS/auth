package com.dmitrenko.database.service;

import com.dmitrenko.database.model.dto.RoleAddRequest;
import com.dmitrenko.database.model.dto.RoleUpdateRequest;
import com.dmitrenko.database.model.dto.RoleView;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    RoleView add(RoleAddRequest request);

    RoleView update(RoleUpdateRequest request);

    RoleView get(UUID id);

    List<RoleView> getAll();

    boolean delete(UUID id);
}
