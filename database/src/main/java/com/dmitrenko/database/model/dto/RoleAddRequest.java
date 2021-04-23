package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleAddRequest {
    private String description;
    private String roleType;
}
