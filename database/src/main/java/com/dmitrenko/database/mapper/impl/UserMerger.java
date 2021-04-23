package com.dmitrenko.database.mapper.impl;

import com.dmitrenko.database.mapper.Merger;
import com.dmitrenko.database.model.domain.User;
import com.dmitrenko.database.model.dto.UserPasswordUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMerger implements Merger<User, UserPasswordUpdateRequest> {

    @Override
    public User merge(User target, UserPasswordUpdateRequest source) {
        if (source.getPassword() != null && !source.getPassword().isBlank()) target.setPassword(source.getPassword());

        return target;
    }
}
