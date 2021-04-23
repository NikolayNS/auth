package com.dmitrenko.database.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Triple<L, C, R> {
    private L left;
    private C centre;
    private R right;
}
