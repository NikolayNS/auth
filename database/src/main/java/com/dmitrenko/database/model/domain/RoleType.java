package com.dmitrenko.database.model.domain;

import java.util.Arrays;

public enum RoleType {
    ADMIN("ADMIN"),
    DEVELOPER("DEVELOPER"),
    ANALYST("ANALYST"),
    USER("USER"),
    CLIENT("CLIENT");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    public static RoleType fromValue(String value) {
        return Arrays.stream(RoleType.values())
                .filter(o -> o.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected value '" + value + "'"));
    }

    public String getValue() {
        return value;
    }
}
