package com.api.bci.users.domain.port;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;

@FunctionalInterface
public interface CreateUserRepository {
    UserResponse execute(UserRequest userRequest);
}
