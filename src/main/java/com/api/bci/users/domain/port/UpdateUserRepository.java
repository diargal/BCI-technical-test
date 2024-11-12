package com.api.bci.users.domain.port;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;

import java.util.UUID;

@FunctionalInterface
public interface UpdateUserRepository {
    UserResponse execute(UUID id, UserRequest userRequest);

}
