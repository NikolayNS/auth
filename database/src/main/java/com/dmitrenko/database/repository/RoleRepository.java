package com.dmitrenko.database.repository;

import com.dmitrenko.database.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByRoleTypeValue(String roleType);

    List<Role> findAllByRoleTypeValueIn(Set<String> roleValues);
}
