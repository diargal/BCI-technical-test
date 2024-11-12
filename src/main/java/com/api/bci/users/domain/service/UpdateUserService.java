package com.api.bci.users.domain.service;

import com.api.bci.users.domain.model.UserRequest;
import com.api.bci.users.domain.model.UserResponse;
import com.api.bci.users.domain.port.UpdateUserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateUserService {
    private final UpdateUserRepository repository;

    public UpdateUserService(UpdateUserRepository repository) {
        this.repository = repository;
    }

    public UserResponse execute(UUID id, UserRequest userRequest) {
        userRequest.setModified(LocalDateTime.now());
        return repository.execute(id, userRequest);
    }
}
