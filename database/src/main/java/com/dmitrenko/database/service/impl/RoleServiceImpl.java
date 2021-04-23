package com.dmitrenko.database.service.impl;

import com.dmitrenko.database.mapper.impl.RoleMapper;
import com.dmitrenko.database.mapper.impl.RoleMerger;
import com.dmitrenko.database.mapper.impl.RoleViewMapper;
import com.dmitrenko.database.model.domain.Role;
import com.dmitrenko.database.model.dto.RoleAddRequest;
import com.dmitrenko.database.model.dto.RoleUpdateRequest;
import com.dmitrenko.database.model.dto.RoleView;
import com.dmitrenko.database.repository.RoleRepository;
import com.dmitrenko.database.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleViewMapper roleViewMapper;
    private final RoleMapper roleMapper;
    private final RoleMerger roleMerger;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleView add(RoleAddRequest request) {
        if (roleRepository.findByRoleTypeValue(request.getRoleType()).isPresent())
            throw new EntityExistsException(String.format("Role %s with description %s already exist", request.getRoleType(), request.getDescription()));

        var role = roleMapper.from(request);
        role = roleRepository.saveAndFlush(role);

        return roleViewMapper.from(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleView update(RoleUpdateRequest request) {
        var role = getOrTrow(request.getId());
        role = roleMerger.merge(role, request);

        return roleViewMapper.from(role);
    }

    private Role getOrTrow(UUID id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role %s not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleView get(UUID id) {
        return roleViewMapper.from(getOrTrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleView> getAll() {
        return roleViewMapper.from(roleRepository.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(UUID id) {
        var role = getOrTrow(id);
        roleRepository.delete(role);
        roleRepository.flush();

        return roleRepository.existsById(id);
    }
}
