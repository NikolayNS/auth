package com.dmitrenko.database.service;

import com.dmitrenko.database.model.dto.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    UserView add(UserAddRequest request);

    UserView updatePassword(UserPasswordUpdateRequest request);

    UserView get(UUID id);

    List<UserView> getAll();

    boolean delete(UUID id);

    UserView addRoles(UUID userId, Set<String> roleValues);

    UserView deleteRoles(UUID userId, Set<String> roleValues);
}
