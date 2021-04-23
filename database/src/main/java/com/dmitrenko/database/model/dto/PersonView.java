package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class PersonView {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String county;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
