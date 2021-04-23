package com.dmitrenko.database.service.impl;

import com.dmitrenko.database.mapper.impl.UserMapper;
import com.dmitrenko.database.mapper.impl.UserMerger;
import com.dmitrenko.database.mapper.impl.UserViewMapper;
import com.dmitrenko.database.model.domain.Person;
import com.dmitrenko.database.model.domain.Role;
import com.dmitrenko.database.model.domain.User;
import com.dmitrenko.database.model.dto.UserAddRequest;
import com.dmitrenko.database.model.dto.UserPasswordUpdateRequest;
import com.dmitrenko.database.model.dto.UserView;
import com.dmitrenko.database.repository.PersonRepository;
import com.dmitrenko.database.repository.RoleRepository;
import com.dmitrenko.database.repository.UserRepository;
import com.dmitrenko.database.service.UserService;
import com.dmitrenko.database.util.Triple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    private final UserViewMapper userViewMapper;
    private final UserMapper userMapper;
    private final UserMerger userMerger;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserView add(UserAddRequest request) {
        if (userRepository.findByLoginAndPersonId(request.getLogin(), request.getPersonId()).isPresent())
            throw new EntityExistsException(String.format("User %s with person %s already exist", request.getLogin(), request.getPersonId()));

        var user = userMapper.from(new Triple<UserAddRequest, Person, List<Role>>()
                .setLeft(request)
                .setCentre(personRepository.findById(request.getPersonId())
                        .orElseThrow(() -> new EntityNotFoundException(String.format("Person %s not found", request.getPersonId()))))
                .setRight(roleRepository.findAllByRoleTypeValueIn(request.getRoleValues())));
        user = userRepository.saveAndFlush(user);

        return userViewMapper.from(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserView updatePassword(UserPasswordUpdateRequest request) {
        var user = getOrTrow(request.getId());
        user = userMerger.merge(user, request);

        return userViewMapper.from(user);
    }

    private User getOrTrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User %s not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public UserView get(UUID id) {
        return userViewMapper.from(getOrTrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserView> getAll() {
        return userViewMapper.from(userRepository.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(UUID id) {
        var user = getOrTrow(id);
        userRepository.delete(user);
        userRepository.flush();

        return userRepository.existsById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserView addRoles(UUID userId, Set<String> roleValues) {
        var user = getOrTrow(userId);
        var addRoles = roleRepository.findAllByRoleTypeValueIn(roleValues);
        user.getRoles().addAll(addRoles);
        userRepository.saveAndFlush(user);

        return userViewMapper.from(getOrTrow(userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserView deleteRoles(UUID userId, Set<String> roleValues) {
        var user = getOrTrow(userId);
        var addRoles = roleRepository.findAllByRoleTypeValueIn(roleValues);
        user.getRoles().removeAll(addRoles);
        userRepository.saveAndFlush(user);

        return userViewMapper.from(getOrTrow(userId));
    }
}
