package com.api.bci.users.domain.port;

import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;

@FunctionalInterface
public interface AuthRepository {
    LoginResponse execute(LoginRequest loginRequest);
}
