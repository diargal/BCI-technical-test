package com.api.bci.users.infrastructure.adapter.security;

import com.api.bci.users.domain.model.LoginRequest;
import com.api.bci.users.domain.model.LoginResponse;
import com.api.bci.users.domain.port.AuthRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JwtAdapter implements AuthRepository {
    private final JwtUtils jwtUtils;

    public JwtAdapter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {
        return new LoginResponse(jwtUtils.generateAccesToken(loginRequest.getEmail()));
    }
}