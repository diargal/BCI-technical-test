package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;
import com.api.bci.users.domain.port.AuthRepository;

public class LoginService {
    private final AuthRepository repository;

    public LoginService(AuthRepository repository) {
        this.repository = repository;
    }

    public LoginResponse execute(LoginRequest loginRequest) {
        return repository.execute(loginRequest);
    }
}
