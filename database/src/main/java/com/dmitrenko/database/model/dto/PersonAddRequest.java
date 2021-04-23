package com.dmitrenko.database.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PersonAddRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String county;
}
