package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;
import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.AuthRepository;
import com.api.bci.users.domain.port.CreateUserRepository;
import com.api.bci.users.infrastructure.adapter.security.model.enums.RoleEnum;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateUserService {
    private final CreateUserRepository createUserRepository;
    private final AuthRepository authRepository;

    public CreateUserService(CreateUserRepository createUserRepository, AuthRepository authRepository) {
        this.createUserRepository = createUserRepository;
        this.authRepository = authRepository;
    }

    public UserResponse execute(UserRequest userRequest) {
        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());
        LocalDateTime dateTimeNow = LocalDateTime.now();
        userRequest.setCreated(dateTimeNow);
        userRequest.setLastLogin(dateTimeNow);
        userRequest.setModified(dateTimeNow);
        userRequest.setActive(Boolean.TRUE);
        userRequest.setRoles(Set.of(RoleEnum.USER));
        UserResponse userResponse = createUserRepository.execute(userRequest);
        LoginResponse loginResponse = authRepository.execute(loginRequest);
        userResponse.setToken(loginResponse.getToken());

        return userResponse;
    }
}
