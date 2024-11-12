package com.api.bci.users.domain.port;

import com.api.bci.users.domain.model.UserResponse;

import java.util.UUID;

@FunctionalInterface
public interface FindUserRepository {
    UserResponse execute(String email);
}
