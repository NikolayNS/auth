package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class UserPasswordUpdateRequest {
    private UUID id;
    private String password;
    private List<UUID> roles = Collections.emptyList();
}
