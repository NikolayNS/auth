package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class UserView {
    private UUID id;
    private String login;
    private String password;
    private PersonView personView;
    private List<RoleView> roleViews;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
