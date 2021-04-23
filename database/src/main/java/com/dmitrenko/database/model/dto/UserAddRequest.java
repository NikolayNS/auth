package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;

@Data
@Accessors(chain = true)
public class UserAddRequest {
    private String login;
    private String password;
    private UUID personId;
    private Set<String> roleValues = new HashSet<>();
}
