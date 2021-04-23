package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class RoleUpdateRequest {
    private UUID id;
    private String description;
    private String roleType;
}
