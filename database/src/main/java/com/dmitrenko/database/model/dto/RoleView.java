package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class RoleView {
    private UUID id;
    private String description;
    private String roleType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
